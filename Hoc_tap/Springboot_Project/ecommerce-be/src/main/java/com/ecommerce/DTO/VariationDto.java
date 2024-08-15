package com.ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationDto {
    private String variationName;
    private Long productId;
    private List<VariationOptionRequest> options;
}
