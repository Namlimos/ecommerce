package com.ecommerce.Controller;

import com.ecommerce.DTO.AttributeRequest;
import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.Entity.Attribute;
import com.ecommerce.Service.AttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attribute")
@RequiredArgsConstructor
public class AttributeController {
    private final AttributeService attributeService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<AttributeRequest>> addAttribute(@RequestBody AttributeRequest attributeRequest) {
        return attributeService.addAttribute(attributeRequest);
    }


}
