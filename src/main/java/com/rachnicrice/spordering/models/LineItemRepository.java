package com.rachnicrice.spordering.models;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LineItemRepository extends JpaRepository<LineItem, Long> {
}
