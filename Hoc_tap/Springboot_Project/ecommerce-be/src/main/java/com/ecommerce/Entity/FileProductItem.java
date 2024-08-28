package com.ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "file_product_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fileId;

    private Long productItemId;

    private Long categoryId;


    public FileProductItem id(final Long id) {
        this.id = id;
        return this;
    }

    public FileProductItem fileId(final Long fileId) {
        this.fileId = fileId;
        return this;
    }

    public FileProductItem productItemId(final Long productItemId) {
        this.productItemId = productItemId;
        return this;
    }

    public FileProductItem categoryId(final Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

}
