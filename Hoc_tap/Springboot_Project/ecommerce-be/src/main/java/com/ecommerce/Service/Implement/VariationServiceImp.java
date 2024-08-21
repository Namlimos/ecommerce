package com.ecommerce.Service.Implement;

import com.ecommerce.DTO.AttributeValueRequest;
import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.VariationDto;
import com.ecommerce.Entity.ProductItem;
import com.ecommerce.Entity.Variation;
import com.ecommerce.Repository.ProductItemRepository;
import com.ecommerce.Repository.VariationRepository;
import com.ecommerce.Service.VariationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VariationServiceImp implements VariationService {
    private final ModelMapper modelMapper;
    private final VariationRepository variationRepository;
    private final ProductItemRepository productItemRepository;


    @Override
    public ResponseEntity<BaseResponse<VariationDto>> addVariation(VariationDto variationDto) {
        Variation variation = modelMapper.map(variationDto, Variation.class);
        ProductItem item = productItemRepository.findById(variationDto.getProductItemId())
                .orElse(null);
        variation.setProductItemId(item.getId());
        variationRepository.save(variation);

        for(AttributeValueRequest attributeValueRequest : variationDto.getAttributeValueRequests()) {


        }
        return null;
    }
}
