package com.ecommerce.Controller;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.ProductVariationRequest;
import com.ecommerce.Service.ProductVariationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/variation")
@RequiredArgsConstructor
public class VariationController {

    private final ProductVariationService productVariationService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<ProductVariationRequest>> addVariation(@RequestBody ProductVariationRequest productVariationRequest) {
        return productVariationService.addVariation(productVariationRequest);
    }

}
