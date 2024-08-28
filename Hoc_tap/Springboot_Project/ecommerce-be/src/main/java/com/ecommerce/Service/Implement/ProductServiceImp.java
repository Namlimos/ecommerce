package com.ecommerce.Service.Implement;

import com.ecommerce.DTO.*;
import com.ecommerce.Entity.*;
import com.ecommerce.Repository.*;
import com.ecommerce.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductItemRepository productItemRepository;
    private final CategoryRepository categoryRepository;
    private final FileProductItemRepository fileProductItemRepository;
    private final FileAppRepository fileAppRepository;
    private final ModelMapper modelMapper;
    private final ProductVariationRepository productVariationRepository;
    private final VariationAttributeValueRepository variationAttributeValueRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeValueRepository attributeValueRepository;

    @Override
    public ResponseEntity<BaseResponse<ProductResponse>> addProduct(ProductRequest productRequest) {
        ProductItem product = modelMapper.map(productRequest, ProductItem.class);

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategoryId(category.getId());
        product = productItemRepository.save(product);

        ProductResponse productResponse = null;


        if (productRequest.getFileId() != null && !productRequest.getFileId().isEmpty()) {
            for (String fileIdStr : productRequest.getFileId()) {
                Long fileId = Long.parseLong(fileIdStr);
                FileProductItem fileProductItem = new FileProductItem();
                fileProductItem.setFileId(fileId);
                fileProductItem.setProductItemId(product.getId());
                // Save the FileProductItem entity
                fileProductItemRepository.save(fileProductItem);
            }
            // Map the saved ProductItem entity to the ProductResponse DTO
            productResponse = modelMapper.map(product, ProductResponse.class);

        }            // Return a successful response with the ProductResponse
        return ResponseEntity.ok(new BaseResponse<>(
                ResponseCode.SUCCESS.getCode(),
                "Product uploaded successfully",
                productResponse
        ));
    }


    @Override
    public ResponseEntity<BaseResponse<List<ProductResponse>>> getAllProduct() {
        List<ProductResponse> productResponses = productItemRepository.findAll().stream()
                .map(item -> {
                    ProductResponse productResponse = modelMapper.map(item, ProductResponse.class);
                    List<FileAppDto> files = fileProductItemRepository.findAllByProductItemId(item.getId()).stream()
                            .map(file -> modelMapper.map(file, FileAppDto.class))
                            .collect(Collectors.toList());
//                    productResponse.setFiles(files);
                    return productResponse;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(new BaseResponse<>(
                ResponseCode.SUCCESS.getCode(),
                "Products fetched successfully",
                productResponses
        ));
    }


    @Override
    public ResponseEntity<BaseResponse<ProductResponse>> getProductById(Long productItemId) {
        // Fetch the product item by its ID
        ProductItem item = productItemRepository.findById(productItemId)
                .orElseThrow(() -> new RuntimeException("Product Item not found"));

        // Map the ProductItem entity to ProductResponse DTO
        ProductResponse productResponse = modelMapper.map(item, ProductResponse.class);

        // Fetch product variations from the repository
        List<ProductVariation> productVariations = productVariationRepository.findByProductItemId(item.getId());

        // Create a list to hold VariantDto objects
        List<ProductResponse.VariantDto> variantsDto = new ArrayList<>();

        // Loop through each product variation and map it to a VariantDto
        for (ProductVariation productVariation : productVariations) {
            ProductResponse.VariantDto variantDto = new ProductResponse.VariantDto();
            variantDto.setSku(productVariation.getSku());
            variantDto.setPrice(productVariation.getPrice());
            variantDto.setStockQuantity(productVariation.getQuantity());
            // Add the mapped VariantDto to the list of variants
            variantsDto.add(variantDto);

            List<ProductResponse.VariantDto.AttributeValueDto> attributeValuesDto = new ArrayList<>();
            List<VariationAttributeValue> variationAttributeValues  = variationAttributeValueRepository.findByVariationId(productVariation.getId());

            for(VariationAttributeValue variationAttributeValue : variationAttributeValues){
                AttributeValue attributeValue = attributeValueRepository.findById(variationAttributeValue.getAttributeValueId())
                        .orElseThrow(() -> new RuntimeException("Attribute Value not found"));
                // Map AttributeValue to AttributeValueDto
                ProductResponse.VariantDto.AttributeValueDto attributeValueDto = new ProductResponse.VariantDto.AttributeValueDto();
                Attribute attribute = attributeRepository.findById(attributeValue.getAttributeId())
                        .orElseThrow(() -> new RuntimeException("Attribute not found"));
                attributeValueDto.setAttributeName(attribute.getAttributeName()); // assuming this is the correct attribute id
                attributeValueDto.setValue(attributeValue.getValue()); // set the actual value
                attributeValuesDto.add(attributeValueDto);
            }
            variantDto.setAttributes(attributeValuesDto);
        }

        // Set the list of VariantDto in the product response
        productResponse.setVariants(variantsDto);

        // Fetch files and their corresponding URLs
        List<FileProductItemDto> files = fileProductItemRepository.findAllByProductItemId(item.getId()).stream()
                .map(fileProductItem -> {
                    FileProductItemDto fileProductItemDto = modelMapper.map(fileProductItem, FileProductItemDto.class);

                    // Fetch the corresponding FileApp entity using the fileId
                    FileApp fileApp = fileAppRepository.findById(fileProductItemDto.getFileId())
                            .orElseThrow(() -> new RuntimeException("File not found"));

                    // Set the fileUrl in FileProductItemDto
                    fileProductItemDto.setFileUrl(fileApp.getFileUrl());

                    return fileProductItemDto;
                })
                .collect(Collectors.toList());

        // Set the list of files in the product response
        productResponse.setFiles(files);

        // Return a successful response with the ProductResponse
        return ResponseEntity.ok(new BaseResponse<>(
                ResponseCode.SUCCESS.getCode(),
                "Product fetched successfully",
                productResponse
        ));
    }



    @Override
    public ResponseEntity<BaseResponse<ProductResponse>> modifyProductById(Long productItemId, ProductRequest productRequest) {
        ProductItem productItem = productItemRepository.findById(productItemId)
                .orElseThrow(() -> new RuntimeException("Product Item not found"));
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        productItem.setCategoryId(category.getId());

        modelMapper.map(productRequest, productItem);
        productItem = productItemRepository.save(productItem);
        ProductResponse productResponse = modelMapper.map(productItem, ProductResponse.class);

        if (productRequest.getFileId() != null && !productRequest.getFileId().isEmpty()) {
            for (String fileIdStr : productRequest.getFileId()) {
                Long fileId = Long.parseLong(fileIdStr);

                FileProductItem fileProductItem = new FileProductItem();
                fileProductItem.setFileId(fileId);
                fileProductItem.setProductItemId(productItem.getId());
                // Save the FileProductItem entity
                fileProductItemRepository.save(fileProductItem);
            }
            // Map the saved ProductItem entity to the ProductResponse DTO
            productResponse = modelMapper.map(productItem, ProductResponse.class);
            productResponse.setCategoryId(category.getId());
        }
        // Return a successful response with the ProductResponse
        return ResponseEntity.ok(new BaseResponse<>(
                ResponseCode.SUCCESS.getCode(),
                "Product modified successfully",
                productResponse
        ));}

    @Override
    public ResponseEntity<BaseResponse<String>> deleteProductById(Long productItemId) {
        ProductItem productItem = productItemRepository.findById(productItemId)
                .orElseThrow(() -> new RuntimeException("Product Item not found"));

        productItemRepository.delete(productItem);
        return ResponseEntity.ok(new BaseResponse<>(
                ResponseCode.SUCCESS.getCode(),
                "Product deleted successfully",
                null
        ));
    }


}
