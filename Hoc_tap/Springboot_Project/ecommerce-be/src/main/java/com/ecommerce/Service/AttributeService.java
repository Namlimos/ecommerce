package com.ecommerce.Service;

import com.ecommerce.DTO.AttributeRequest;
import com.ecommerce.DTO.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface AttributeService {
    ResponseEntity<BaseResponse<AttributeRequest>> addAttribute(AttributeRequest attributeRequest);
}
