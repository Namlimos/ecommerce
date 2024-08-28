package com.ecommerce.Repository;

import com.ecommerce.Entity.FileProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FileProductItemRepository extends JpaRepository<FileProductItem, Long> {
    List<FileProductItem> findAllByProductItemId(Long id);
}
