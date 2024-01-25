package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.query.Order;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
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

//    @Column(name = "godown_id")
//    private int godownId;
//
//    public int getGodownId() {
//        return godownId;
//    }
//
//    public void setGodownId(int godownId) {
//        this.godownId = godownId;
//    }

    public Product(){}
    public Product(String productName, int productVolume, float price, int totalQuantity) {
        this.productName = productName;
        this.productVolume = productVolume;
        this.price = price;
        this.totalQuantity = totalQuantity;
    }
}
