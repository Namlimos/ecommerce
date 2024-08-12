package com.ecommerce.Controller;

import com.ecommerce.DTO.BaseResponse;
import com.ecommerce.DTO.CategoryRequest;
import com.ecommerce.Entity.Category;
import com.ecommerce.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController{

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse<String>> addCategory(@RequestBody CategoryRequest category){
       return categoryService.addCategory(category);

    }

}
