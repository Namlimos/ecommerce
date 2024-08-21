package com.ecommerce.Controller;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.VariationDto;
import com.ecommerce.Service.VariationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variation")
@RequiredArgsConstructor
public class VariationController {

    private final VariationService variationService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<VariationDto>> addVariation(@RequestBody VariationDto variationDto) {
        return variationService.addVariation(variationDto);
    }

}
