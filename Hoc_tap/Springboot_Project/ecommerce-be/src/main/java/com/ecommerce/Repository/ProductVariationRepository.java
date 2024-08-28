package com.ecommerce.Repository;

import com.ecommerce.Entity.ProductVariation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariationRepository extends JpaRepository<ProductVariation, Long> {

  List<ProductVariation> findByProductItemId(Long id);
}