package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;
import org.hibernate.query.Order;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId; // prod id

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_volume")
    private int productVolume;

    @Column(name = "price")
    private float price;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "godown_id")
    private int godownId;

    public int getGodownId() {
        return godownId;
    }

    public void setGodownId(int godownId) {
        this.godownId = godownId;
    }

    public Product(){}

    public Product(int productId, String productName, int productVolume, float price, int totalQuantity, int godownId) {
        this.productId = productId;
        this.productName = productName;
        this.productVolume = productVolume;
        this.price = price;
        this.totalQuantity = totalQuantity;
        this.godownId = godownId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productVolume=" + productVolume +
                ", price=" + price +
                ", totalQuantity=" + totalQuantity +
                ", godownId=" + godownId +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductVolume() {
        return productVolume;
    }

    public void setProductVolume(int productVolume) {
        this.productVolume = productVolume;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
