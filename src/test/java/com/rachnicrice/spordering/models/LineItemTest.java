package com.rachnicrice.spordering.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LineItemTest {
    @Autowired
    OrderRepository orepo;

    @Autowired
    ProductRepository prepo;

    LineItem item;
    Order order;
    Product prod;

    @BeforeEach
    public void setUp() {
        order = orepo.getOne(1L);
        prod = prepo.getOne(1L);
        item = new LineItem(order, prod, 12);
    }

    @Test
    public void testGetOrder () {
        Long id = item.getOrder().getOrder_id();
        assertEquals(id, 1);
    }

    @Test
    public void testSetOrder () {
        item.setOrder(orepo.getOne(2L));
        assertEquals(2L, item.getOrder().getOrder_id());
    }

    @Test
    public void testGetProduct () {
        assertEquals(1L, item.getProduct().getItem_id());
    }

    @Test
    public void testSetProduct () {
        item.setProduct(prepo.getOne(2L));
        assertEquals(2L, item.getProduct().getItem_id());

    }

    @Test
    public void testGetQuantity () {
        assertEquals(12, item.getQuantity());
    }

    @Test
    public void testSetQuantity () {
        item.setQuantity(5);
        assertEquals(5, item.getQuantity());
    }
}
