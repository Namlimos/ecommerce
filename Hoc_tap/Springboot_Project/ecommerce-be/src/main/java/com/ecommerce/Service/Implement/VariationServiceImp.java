package com.ecommerce.Service.Implement;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.ResponseCode;
import com.ecommerce.DTO.VariationDto;
import com.ecommerce.DTO.VariationOptionRequest;
import com.ecommerce.Entity.ProductItem;
import com.ecommerce.Entity.Variation;
import com.ecommerce.Entity.VariationOption;
import com.ecommerce.Repository.ProductItemRepository;
import com.ecommerce.Repository.VariationOptionRepository;
import com.ecommerce.Repository.VariationRepository;
import com.ecommerce.Service.VariationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VariationServiceImp implements VariationService {
    private final ModelMapper modelMapper;
    private final VariationRepository variationRepository;
    private final ProductItemRepository productItemRepository;
    private final VariationOptionRepository variationOptionRepository;


    @Override
    public ResponseEntity<BaseResponse<VariationDto>> addVariationToProduct(VariationDto variationDto) {
        // Fetch the product item and handle the exception properly
        ProductItem productItem = productItemRepository.findById(variationDto.getProductItemId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Map the VariationDto to the Variation entity and associate it with the product item
        Variation variation = modelMapper.map(variationDto, Variation.class);
        variation.setProductItemId(productItem.getId());

        // Save the variation first to ensure it has an ID for the variation options
        variation = variationRepository.save(variation);

        // Map and save the variation options

        Variation finalVariation = variation;
        List<VariationOption> variationOptions = variationDto.getOptions().stream()
                .map(optionRequest -> {
                    VariationOption variationOption = modelMapper.map(optionRequest, VariationOption.class);
                    variationOption.setVariationId(finalVariation.getId());
                    return variationOption;
                })
                .collect(Collectors.toList());

        variationOptionRepository.saveAll(variationOptions);

        // Update the variationDto with the saved variation's ID (if needed)


        // Return a successful response with the updated variationDto
        return ResponseEntity.ok(new BaseResponse<>(
                ResponseCode.SUCCESS.getCode(),
                "Variation added successfully",
                variationDto
        ));
    }

}
