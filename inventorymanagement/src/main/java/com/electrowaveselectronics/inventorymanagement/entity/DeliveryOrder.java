package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;



@Entity
@Table(name = "delivery_order")
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_quantity")
    private String orderQuantity;

    @Column(name = "total_sell_price")
    private String totalSellPrice;

    @Column(name = "tax")
    private int tax;

    @Column(name = "expected_date")
    private int expectedDate;

    public DeliveryOrder(){}

    public DeliveryOrder(String orderQuantity, String totalSellPrice, int tax, int expectedDate) {
        this.orderQuantity = orderQuantity;
        this.totalSellPrice = totalSellPrice;
        this.tax = tax;
        this.expectedDate = expectedDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getTotalSellPrice() {
        return totalSellPrice;
    }

    public void setTotalSellPrice(String totalSellPrice) {
        this.totalSellPrice = totalSellPrice;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(int expectedDate) {
        this.expectedDate = expectedDate;
    }
}
