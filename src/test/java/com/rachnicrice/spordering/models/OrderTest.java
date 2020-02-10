package com.rachnicrice.spordering.models;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    ApplicationUser appuser;
    Order order;
    LineItem lineItem;
    Product product;
    Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @BeforeEach
    void setUp() {
        appuser = new ApplicationUser("testusername", "testpassword","test@email.com", "222-222-2222", "testFirstName", "testLastname","testCustomerNumber", "test business name", false);
        order = new Order(appuser, createdAt, false);
        product = new Product("DS2310BLK-LF", "Downspout", "2x3", "10'", "Black", "style", "downspout", 10);

        lineItem = new LineItem(order, product,20);

    }

    @Test
    void getDate_created() {
        assertEquals(createdAt, order.getDate_created());
    }

    @Test
    void getSubmitted() {
        assertEquals(false,order.getSubmitted());
    }

    @Test
    void getUser() {
        assertEquals(appuser, order.getUser());
    }

    @Test
    void setSubmitted() {
        order.setSubmitted(true);
        assertTrue(order.getSubmitted());
    }

}