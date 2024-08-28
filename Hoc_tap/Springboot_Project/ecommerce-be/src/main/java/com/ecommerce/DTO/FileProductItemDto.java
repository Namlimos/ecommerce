package com.ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileProductItemDto {

    private Long fileId;

    private Long productItemId;

    private Long categoryId;
    private String fileUrl;  // Add this field

}
