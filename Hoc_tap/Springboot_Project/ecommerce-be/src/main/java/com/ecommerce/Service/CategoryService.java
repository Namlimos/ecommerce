package com.ecommerce.Service;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.CategoryRequest;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<BaseResponse<String>> addCategory(CategoryRequest categoryRequest);
}
