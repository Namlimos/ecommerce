package com.ecommerce.Service;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.VariationDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VariationService {
    ResponseEntity<BaseResponse<VariationDto>> addVariation(VariationDto variationDto);
}
