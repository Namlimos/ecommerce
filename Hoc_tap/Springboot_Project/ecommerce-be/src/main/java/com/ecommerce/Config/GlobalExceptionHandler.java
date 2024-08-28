//package com.ecommerce.Config;
//
//import com.ecommerce.DTO.BaseResponse;
//import com.ecommerce.DTO.ResponseCode;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<BaseResponse<String>> handleAllExceptions(Exception ex, WebRequest request) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>(
//                ResponseCode.INTERNAL_SERVER_ERROR.getCode(),
//                ex.getMessage(),
//                null
//        ));
//    }
//}