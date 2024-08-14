package com.ecommerce.Service;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ResponseEntity<BaseResponse<String>> uploadImages(MultipartFile[] images, Long draftProductId);

    ResponseEntity<BaseResponse<String>> addProduct(ProductRequest productRequest);
}
