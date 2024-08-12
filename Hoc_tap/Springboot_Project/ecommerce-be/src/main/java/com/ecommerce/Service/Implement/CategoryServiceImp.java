package com.ecommerce.Service.Implement;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.CategoryRequest;
import com.ecommerce.DTO.ResponseCode;
import com.ecommerce.Entity.Category;
import com.ecommerce.Repository.CategoryRepository;
import com.ecommerce.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

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

            // Create and save the new category
            Category category = new Category();
            category.setCategoryName(categoryRequest.getCategoryName());
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

}
