package com.rachnicrice.spordering.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Product product;

    int quantity;

    @ManyToOne
    Order order;

    public LineItem() {

    }

    public LineItem(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public LineItem(List<Order> userOrder, Product product, int quantity) {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product lineItem) {
        this.product = lineItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

}
