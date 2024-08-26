package com.ecommerce.Repository;

import com.ecommerce.Entity.VariationAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VariationAttributeValueRepository extends JpaRepository<VariationAttributeValue, Long> {
    List<VariationAttributeValue> findByVariationId(Long variationId);


}
