package com.ecommerce.Service.Implement;

import com.ecommerce.DTO.*;
import com.ecommerce.Entity.*;
import com.ecommerce.Repository.*;
import com.ecommerce.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductItemRepository productItemRepository;
    private final VariationRepository variationRepository;
    private final VariationOptionRepository variationOptionRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;
    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity<BaseResponse<String>> uploadImages(MultipartFile[] images, Long draftProductId) {
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile image : images) {
            try {
                // Định nghĩa đường dẫn lưu trữ tệp
                String uploadDir = "src/main/resources/Product/";
                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename(); // Thêm timestamp để tránh trùng lặp tên tệp

                // Lưu tệp vào hệ thống tệp
                Path path = Paths.get(uploadDir + fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, image.getBytes());

                // Tạo URL truy cập hình ảnh
                String imageUrl = "http://localhost:8080/images/" + fileName;
                imageUrls.add(imageUrl);

                // Lưu thông tin hình ảnh vào cơ sở dữ liệu
                ProductImage productImage = new ProductImage();
                productImage.setDraftProductId(draftProductId);
                productImage.setImageUrl(imageUrl);
                productImageRepository.save(productImage);

            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.ok(new BaseResponse<>(
                ResponseCode.SUCCESS.getCode(),
                "Images uploaded successfully",
                "" + imageUrls
        ));

    }

    @Override
    public ResponseEntity<BaseResponse<ProductResponse>> addProduct(ProductRequest productRequest) {
        ProductItem product = modelMapper.map(productRequest, ProductItem.class);

        Optional<Category> categoryOtp = categoryRepository.findById(productRequest.getCategoryId());
        if(categoryOtp.isPresent()){
            Category category = categoryOtp.get();
            product.setCategory(category);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(
                            ResponseCode.NOT_FOUND.getCode(),
                            "Category not found",
                            null
                    ));
        }

        product = productItemRepository.save(product);
        List<ProductImage> productImages = productImageRepository.findByDraftProductId(productRequest.getDraftProductId());
        if(productImages.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(
                            ResponseCode.NOT_FOUND.getCode(),
                            "Product Id: " + productRequest.getDraftProductId() + "  Not found",
                            null
                    ));
        }
        else {
            for (ProductImage productImage : productImages) {
                productImage.setProductItem(product);
                productImage.setDraftProductId(null); // Clear the draft ID
            }
            productImageRepository.saveAll(productImages); // Save all product images in a single batch operation

            // Map the saved ProductItem entity to the ProductResponse DTO
            ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
            productResponse.setCategoryId(product.getCategory().getId());

            // Return a successful response with the ProductResponse
            return ResponseEntity.ok(new BaseResponse<>(
                    ResponseCode.SUCCESS.getCode(),
                    "Product uploaded successfully",
                    productResponse
            ));
        }


    }

    @Override
    public ResponseEntity<List<ProductItem>> getAllProduct() {
        List<ProductItem> items = productItemRepository.findAll();
        return ResponseEntity.ok().body(items);
    }


}
