package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchase_order")
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


    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "purchase_product",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> orderedProductList;

    public PurchaseOrder(){}

    public PurchaseOrder(Date purchaseDate, int totalCostPrice, int purchaseQuantity) {
        this.purchaseDate = purchaseDate;
        this.totalCostPrice = totalCostPrice;
        this.purchaseQuantity = purchaseQuantity;
    }

    public List<Product> getProductlist() {
        return orderedProductList;
    }

    public void setProductlist(List<Product> productlist) {
        orderedProductList = productlist;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getTotalCostPrice() {
        return totalCostPrice;
    }

    public void setTotalCostPrice(int totalCostPrice) {
        this.totalCostPrice = totalCostPrice;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    // add convenience methods
    public void addProducts(Product tempProduct){
        if(orderedProductList==null){
            orderedProductList = new ArrayList<>();
        }
        orderedProductList.add(tempProduct);
    }
}
