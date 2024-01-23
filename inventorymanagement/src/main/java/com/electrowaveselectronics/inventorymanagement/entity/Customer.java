package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}