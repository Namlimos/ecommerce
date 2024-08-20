package com.ecommerce.Service;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.ProductRequest;
import com.ecommerce.DTO.ProductResponse;
import com.ecommerce.Entity.ProductItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {


    ResponseEntity<BaseResponse<ProductResponse>> addProduct(ProductRequest productRequest);

    ResponseEntity<BaseResponse<List<ProductResponse>>> getAllProduct();

    ResponseEntity<BaseResponse<ProductResponse>> getProductById(Long productItemId);

    ResponseEntity<BaseResponse<ProductResponse>> modifyProductById(Long productItemId, ProductRequest productRequest);

    ResponseEntity<BaseResponse<String>> deleteProductById(Long productItemId);
}
