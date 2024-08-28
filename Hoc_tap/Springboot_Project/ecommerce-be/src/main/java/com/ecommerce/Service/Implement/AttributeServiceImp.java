package com.ecommerce.Service.Implement;

import com.ecommerce.DTO.AttributeRequest;
import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.ResponseCode;
import com.ecommerce.Entity.Attribute;
import com.ecommerce.Repository.AttributeRepository;
import com.ecommerce.Service.AttributeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttributeServiceImp implements AttributeService {

    private final ModelMapper modelMapper;
    private final AttributeRepository attributeRepository;



    @Override
    public ResponseEntity<BaseResponse<AttributeRequest>> addAttribute(AttributeRequest attributeRequest) {
        Attribute attribute = modelMapper.map(attributeRequest, Attribute.class);
        attributeRepository.save(attribute);
        return ResponseEntity.ok(new BaseResponse<>(
                ResponseCode.SUCCESS.getCode(),
                "Product modified successfully",
                attributeRequest
        ));
    }
}
