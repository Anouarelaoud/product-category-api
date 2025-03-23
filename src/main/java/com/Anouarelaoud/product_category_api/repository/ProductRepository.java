package com.Anouarelaoud.product_category_api.repository;

import com.Anouarelaoud.product_category_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
