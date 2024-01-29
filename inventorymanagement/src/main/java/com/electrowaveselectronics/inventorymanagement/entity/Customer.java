package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId; // provider id

    @Column(name = "customer_name")
    private int customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_number")
    private int customerNo;

    public Customer() {
    }

    public Customer(int customerName, String customerAddress, int customerNo) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerNo = customerNo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerName() {
        return customerName;
    }

    public void setCustomerName(int customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }
}
