package com.ecommerce.Repository;

import com.ecommerce.Entity.VariationOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface VariationOptionRepository extends JpaRepository<VariationOption, Long> {

    List<VariationOption> findByVariationId(Long id);
}
