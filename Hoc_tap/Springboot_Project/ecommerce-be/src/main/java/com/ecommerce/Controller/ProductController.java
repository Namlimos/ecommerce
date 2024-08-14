package com.ecommerce.Controller;


import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.ProductRequest;
import com.ecommerce.Service.ProductService;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final String imageDirectory = "src/main/resources/images/";


    @PostMapping("/images/add")
    private ResponseEntity<BaseResponse<String>> uploadImages( @RequestParam("images") MultipartFile[] images,
                                                                     @RequestParam("draftProductId") Long draftProductId){
        return productService.uploadImages(images, draftProductId);
    }
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(imageDirectory).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG) // Hoặc xác định content type dựa vào file extension
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<String>> addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }


}
