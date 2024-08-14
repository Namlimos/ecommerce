package com.ecommerce.Repository;

import com.ecommerce.Entity.Variation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariationRepository extends JpaRepository<Variation, Long> {
}
