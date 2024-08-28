package com.ecommerce.Controller;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.Service.FileAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final String imageDirectory = "src/main/resources/file/";

    private final FileAppService fileAppService;
    @PostMapping("/images/add")
    private ResponseEntity<BaseResponse<String>> uploadImages(@RequestParam("images") MultipartFile[] files){
        return fileAppService.uploadFile(files);
    }
    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(imageDirectory).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Adjust the MIME type according to your file type (e.g., MediaType.IMAGE_PNG)
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
