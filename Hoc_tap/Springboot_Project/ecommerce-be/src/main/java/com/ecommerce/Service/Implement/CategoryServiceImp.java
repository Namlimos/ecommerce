package com.ecommerce.Service.Implement;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.CategoryRequest;
import com.ecommerce.DTO.ResponseCode;
import com.ecommerce.Entity.Category;
import com.ecommerce.Repository.CategoryRepository;
import com.ecommerce.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Value("${category.upload-dir}")
    private String uploadDir;

    @Override
    public ResponseEntity<BaseResponse<String>> addCategory(CategoryRequest categoryRequest) {
        try {
            // Validate the category request
            if (categoryRequest.getCategoryName() == null || categoryRequest.getCategoryName().isEmpty()) {
                return ResponseEntity.badRequest().body(new BaseResponse<>(
                        ResponseCode.BAD_REQUEST.getCode(),
                        "Category name is required",
                        null
                ));
            }

            // Check if the category already exists
            if (categoryRepository.existsByCategoryName(categoryRequest.getCategoryName())) {
                return ResponseEntity.badRequest().body(new BaseResponse<>(
                        ResponseCode.BAD_REQUEST.getCode(),
                        "Category already exists",
                        null
                ));
            }

            MultipartFile file = categoryRequest.getImage();
            String fileName = null;

            if (file != null && !file.isEmpty()) {
                fileName = saveImage(file, categoryRequest.getCategoryName());
            }

            // Create and save the new category
            Category category = new Category();
            category.setCategoryName(categoryRequest.getCategoryName());
            category.setCategoryImage(fileName != null ? uploadDir + fileName : null);

            categoryRepository.save(category);

            // Return a success response
            return ResponseEntity.ok(new BaseResponse<>(
                    ResponseCode.SUCCESS.getCode(),
                    "Category added successfully",
                    "Category ID: " + category.getId()
            ));
        } catch (Exception e) {
            // Log the exception (you may want to use a logger here)
            e.printStackTrace();

            // Return an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>(
                    ResponseCode.INTERNAL_SERVER_ERROR.getCode(),
                    "An unexpected error occurred",
                    null
            ));
        }
    }

    private String saveImage(MultipartFile file, String categoryName) throws IOException {
        // Create the directory if it doesn't exist
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Clean the category name to create a safe file name
        String cleanCategoryName = categoryName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");

        // Get the file extension
        String originalFileName = file.getOriginalFilename();
        String extension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        // Construct the full file name with the category name
        String fileName = cleanCategoryName + extension;

        // Ensure the file name is unique
        Path filePath = Paths.get(uploadDir, fileName);
        int counter = 1;
        while (Files.exists(filePath)) {
            fileName = cleanCategoryName + "_" + counter + extension;
            filePath = Paths.get(uploadDir, fileName);
            counter++;
        }

        // Save the file to the specified directory
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    @Override
    public ResponseEntity<BaseResponse<String>> deleteCategoryByName(String categoryName) {

        try{
            if(!categoryRepository.existsByCategoryName(categoryName)) {
                return ResponseEntity.badRequest().body(new BaseResponse<>(
                        ResponseCode.BAD_REQUEST.getCode(),
                        "Category is not found",
                        null
                ));
            }
            Category category = categoryRepository.findByCategoryName(categoryName);
            categoryRepository.delete(category);
            return ResponseEntity.ok(new BaseResponse<>(
                    ResponseCode.SUCCESS.getCode(),
                    "Category " + categoryName +" is deleted successfully",
                    "Category ID: " + category.getId()
            ));
        }

        catch (Exception e) {
            // Log the exception (you may want to use a logger here)
            e.printStackTrace();

            // Return an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>(
                    ResponseCode.INTERNAL_SERVER_ERROR.getCode(),
                    "An unexpected error occurred",
                    null
            ));
        }

    }

}
