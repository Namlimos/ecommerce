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
        Variation variation = modelMapper.map(variationDto, Variation.class);
        ProductItem productItemOptional = productItemRepository.findById(variationDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        variation.setProductItems(productItemOptional);

//        // Map and set the VariationOptions
//        List<VariationOption> variationOptions = variationDto.getOptions().stream()
//                .map(optionRequest -> {
//                    VariationOption option = modelMapper.map(optionRequest, VariationOption.class);
//                    option.setVariation(variation); // Set the parent Variation
//                    return option;
//                })
//                .collect(Collectors.toList());
//
//        // Save all VariationOptions to the database
//        variationOptionRepository.saveAll(variationOptions);

        List<VariationOption> variationOptions = new ArrayList<>();
        for (VariationOptionRequest optionRequest : variationDto.getOptions()){
            VariationOption variationOption = new VariationOption();
            variationOption.setOptionValue(optionRequest.getOptionValue());
            variationOption.setPrice(optionRequest.getPrice());
            variationOption.setQuantity(optionRequest.getQuantity());
            variationOption.setVariation(variation);
            variationOptions.add(variationOption);
        }
        variation.setVariationOptions(variationOptions);

        variation = variationRepository.save(variation);

        for (VariationOption variationOption : variationOptions) {
            variationOptionRepository.save(variationOption);
        }


        return ResponseEntity.ok(new BaseResponse<>(
                ResponseCode.SUCCESS.getCode(),
                "Product uploaded successfully",
                variationDto
        ));
    }
}
