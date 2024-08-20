package com.ecommerce.Controller;


import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.ProductRequest;
import com.ecommerce.DTO.ProductResponse;
import com.ecommerce.Entity.ProductItem;
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

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;






    @PostMapping("/add")
    public ResponseEntity<BaseResponse<ProductResponse>> addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @GetMapping("/get/all-Product")
    public ResponseEntity<BaseResponse<List<ProductResponse>>> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/get/{productItemId}")
    public ResponseEntity<BaseResponse<ProductResponse>> getProductById(@PathVariable Long productItemId){
        return productService.getProductById(productItemId);
    }

    @PostMapping("/modify")
    public ResponseEntity<BaseResponse<ProductResponse>> modifyProductById(
            @RequestParam("productItemId") Long productItemId, @RequestBody ProductRequest productRequest ){
        return productService.modifyProductById(productItemId, productRequest);
    }

    @PostMapping("/delete}")
    public ResponseEntity<BaseResponse<String>> deleteProductById(@RequestParam("productItemId") Long productItemId){
        return productService.deleteProductById(productItemId);
    }
}
