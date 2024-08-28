package com.ecommerce.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BaseResponse<T> {
    private int code;
    private String message;
    private T data;
}
