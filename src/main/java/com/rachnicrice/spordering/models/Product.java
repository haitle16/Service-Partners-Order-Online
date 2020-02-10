package com.rachnicrice.spordering.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;

    private String itemCode;
    private String itemName;
    private String width;
    private String length;
    private String color;
    private String style;
    private String type;
    private int orderIncrement;

    public Product() { }

    public Product(String itemCode, String itemName, String width, String length, String color, String style, String type) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.width = width;
        this.length = length;
        this.color = color;
        this.style = style;
        this.type = type;
    }

    public Product(String itemCode, String itemName, String width, String length, String color, String style, String type, int orderIncrement) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.width = width;
        this.length = length;
        this.color = color;
        this.style = style;
        this.type = type;
        this.orderIncrement = orderIncrement;
    }

    public Product(String itemCode, String itemName, String length, String color, String type) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.length = length;
        this.color = color;
        this.type = type;
    }

    public Product(String itemCode, String itemName, String length, String width, String style, String type) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.width = width;
        this.length = length;
        this.style = style;
        this.type = type;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Long getId() {
        return item_id;
    }

    public void setId(Long id) {
        this.item_id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getOrderIncrement() {
        return orderIncrement;
    }

    @Override
    public String toString() {
        return "Product{" +
                "itemCode='" + itemCode + '\'' +
                ", name='" + itemName + '\'' +
                ", width=" + width +
                ", length=" + length +
                ", color='" + color + '\'' +
                ", style='" + style + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
