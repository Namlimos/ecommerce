package com.ecommerce.Repository;

import com.ecommerce.Entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

}
