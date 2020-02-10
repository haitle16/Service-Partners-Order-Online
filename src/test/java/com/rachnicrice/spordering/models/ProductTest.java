package com.rachnicrice.spordering.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;

    @BeforeEach
    void setUp() {
        product = new Product("DS2310BLK-LF", "Downspout", "2x3", "10'", "Black", "style", "downspout", 10);
    }

    @Test
    void getItemCode() {
        assertEquals("DS2310BLK-LF", product.getItemCode());
    }

    @Test
    void setItemCode() {
        product.setItemCode("DS2310BLK-RT");
        assertEquals("DS2310BLK-RT", product.getItemCode());
    }

    @Test
    void getWidth() {
        assertEquals("Downspout", product.getItemName());
    }

    @Test
    void setWidth() {
        product.setItemName("DownSpoutTest");
        assertEquals("DownSpoutTest", product.getItemName());
    }

    @Test
    void getLength() {
        assertEquals("10'", product.getLength());
    }

    @Test
    void setLength() {
        product.setLength("20'");
        assertEquals("20'", product.getLength());

    }

    @Test
    void getColor() {
        assertEquals("Black", product.getColor());
    }

    @Test
    void setColor() {
        product.setColor("Blue");
        assertEquals("Blue", product.getColor());
    }

    @Test
    void getStyle() {
        assertEquals("style", product.getStyle());
    }

    @Test
    void setStyle() {
        product.setStyle("SomeStyle");
        assertEquals("SomeStyle", product.getStyle());
    }

    @Test
    void getType() {
        assertEquals("downspout", product.getType());
    }

    @Test
    void setType() {
        product.setType("DownSpout Type");
        assertEquals("DownSpout Type", product.getType());
    }

    @Test
    void getItemName() {
        assertEquals("Downspout", product.getItemName());
    }

    @Test
    void setItemName() {
        product.setItemName("DownSpout");
        assertEquals("DownSpout", product.getItemName());
    }

    @Test
    void getOrderIncrement() {
        assertEquals(10, product.getOrderIncrement());
    }

    @Test
    void getItem_id() {
        product.setItem_id((long) 99);
        assertEquals(99, product.getItem_id());
    }

    @Test
    void setItem_id() {
        product.setItem_id((long) 20);
        assertEquals(20, product.getItem_id());

    }

    @Test
    void setId() {
        product.setId((long) 10);
        assertEquals(10, product.getId());
    }

    @Test
    void getId() {
        product.setId((long) 100);
        assertEquals(100, product.getId());
    }

}