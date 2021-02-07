package com.orderserver.product;

import com.orderserver.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findByProviderId(@Param("provider_id") Integer providerId);
}
 