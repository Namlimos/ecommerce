package com.ecommerce.Repository;

import com.ecommerce.Entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {


    AttributeValue findByAttributeIdAndValue(Long attributeId, String value);
}
