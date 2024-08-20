package com.ecommerce.Repository;

import com.ecommerce.Entity.Variation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface VariationRepository extends JpaRepository<Variation, Long> {

    List<Variation> findAllByProductItemId(Long id);
}
