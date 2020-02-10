package com.rachnicrice.spordering.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    Date date_created;
    Date date_submitted;
    boolean isSubmitted;

    @ManyToOne
    ApplicationUser user;

    @OneToMany(mappedBy = "order")
    List<LineItem> lineItemsInThisOrder;

    public Order() {}

    public Order(ApplicationUser user, Date date_created, Boolean isSubmitted) {
        this.user = user;
        this.date_created = date_created;
        this.isSubmitted = isSubmitted;
    }

    public Date getDate_submitted() {
        return date_submitted;
    }

    public Order(ApplicationUser user, Date date_created, Boolean isSubmitted, Date date_submitted) {
        this.user = user;
        this.date_created = date_created;
        this.isSubmitted = isSubmitted;
        this.date_submitted = date_submitted;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public Boolean getSubmitted() {
        return isSubmitted;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public List<LineItem> getItemsInThisOrder() {
        return lineItemsInThisOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", date_created=" + date_created +
                ", isSubmitted=" + isSubmitted +
                ", user=" + user +
                ", lineItemsInThisOrder=" + lineItemsInThisOrder +
                '}';
    }
}
