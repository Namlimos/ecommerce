package com.ecommerce.Service.Implement;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.ProductVariationRequest;
import com.ecommerce.DTO.ResponseCode;
import com.ecommerce.Entity.*;
import com.ecommerce.Repository.*;
import com.ecommerce.Service.ProductVariationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductVariationServiceImp implements ProductVariationService {
    private final ModelMapper modelMapper;
    private final VariationRepository variationRepository;
    private final ProductItemRepository productItemRepository;
    private final AttributeValueRepository attributeValueRepository;
    private final AttributeRepository attributeRepository;
    private final VariationAttributeValueRepository variationAttributeValueRepository;

    @Transactional
    @Override
    public ResponseEntity<BaseResponse<ProductVariationRequest>> addVariation(ProductVariationRequest productVariationRequest) {
        ProductItem productItem = productItemRepository.findById(productVariationRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductVariation productVariation = new ProductVariation();
        productVariation.setProductItemId(productItem.getId());

        for(ProductVariationRequest.VariantDto variantDto : productVariationRequest.getVariants()){
            productVariation.setSku(variantDto.getSku());
            productVariation.setPrice(variantDto.getPrice());
            productVariation.setQuantity(variantDto.getStockQuantity());
            productVariation.setPrice(variantDto.getPrice());
            variationRepository.save(productVariation);

            for(ProductVariationRequest.VariantDto.AttributeDto attributeDto : variantDto.getAttributes()){
                Attribute attribute = attributeRepository.findById(attributeDto.getAttributeId())
                        .orElseThrow(() -> new RuntimeException("Attribute not found"));
                AttributeValue attributeValue = attributeValueRepository.findByAttributeIdAndValue(attributeDto.getAttributeId(), attributeDto.getValue());
                if(attributeValue == null){
                    attributeValue = new AttributeValue();
                    attributeValue.setAttributeId(attribute.getId());
                    attributeValue.setValue(attributeDto.getValue());
                    attributeValue = attributeValueRepository.save(attributeValue);
                }
                VariationAttributeValue variationAttributeValue = new VariationAttributeValue();
                variationAttributeValue.setVariationId(productVariation.getId());
                variationAttributeValue.setAttributeValueId(attributeValue.getId());
                variationAttributeValueRepository.save(variationAttributeValue);
            }
        }
        return ResponseEntity.ok(new BaseResponse<>(
                ResponseCode.SUCCESS.getCode(),
                "Product fetched successfully",
                productVariationRequest
        ));
    }
}
