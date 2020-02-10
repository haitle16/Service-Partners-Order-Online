package com.rachnicrice.spordering.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByIsSubmittedFalse();
}
