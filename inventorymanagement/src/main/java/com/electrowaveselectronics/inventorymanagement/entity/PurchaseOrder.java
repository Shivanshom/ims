package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchase_order")
@NoArgsConstructor
@Getter
@Setter
@Data
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private int purchaseId; // prod id

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "total_cost_price")
    private int totalCostPrice;


    @Column(name = "purchase_quantity")
    private int purchaseQuantity;


    @OneToMany(
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    @Column(name = "ordered_product_list")
    private List<Product> orderedProductList;

    @Column
    private int supplierId;


    public PurchaseOrder(Date purchaseDate, int totalCostPrice, int purchaseQuantity, List<Product> orderedProductList, int supplierId) {
        this.purchaseDate = purchaseDate;
        this.totalCostPrice = totalCostPrice;
        this.purchaseQuantity = purchaseQuantity;
        this.orderedProductList = orderedProductList;
        this.supplierId = supplierId;
    }

    // add convenience methods
    public void addProducts(Product tempProduct) {
        if (orderedProductList == null) {
            orderedProductList = new ArrayList<>();
        }
        orderedProductList.add(tempProduct);
    }
}
