package com.ecommerce.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class SaveImageService {

    @Value("${category.upload-dir}")
    private String uploadDir;

    public String saveImage(MultipartFile file, String categoryName) throws IOException {
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
}
