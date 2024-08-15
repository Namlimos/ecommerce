package com.ecommerce.Service;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.VariationDto;
import org.springframework.http.ResponseEntity;

public interface VariationService {
    ResponseEntity<BaseResponse<VariationDto>> addVariationToProduct(VariationDto variationDto);
}
