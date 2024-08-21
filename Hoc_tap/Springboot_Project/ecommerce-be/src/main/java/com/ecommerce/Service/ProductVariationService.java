package com.ecommerce.Service;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.ProductVariationRequest;
import org.springframework.http.ResponseEntity;

public interface ProductVariationService {
    ResponseEntity<BaseResponse<ProductVariationRequest>> addVariation(ProductVariationRequest productVariationRequest);
}
