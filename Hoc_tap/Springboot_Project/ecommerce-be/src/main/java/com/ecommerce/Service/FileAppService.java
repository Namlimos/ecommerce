package com.ecommerce.Service;

import com.ecommerce.DTO.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileAppService {
    ResponseEntity<BaseResponse<String>> uploadFile(MultipartFile[] files);
}
