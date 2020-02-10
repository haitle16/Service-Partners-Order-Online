package com.rachnicrice.spordering.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByItemCode(String itemCode);
}
