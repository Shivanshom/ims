package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery_order")
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

//    foreign key left
   @Column(name ="customer_id")
    private int customerId;


    @Column(name = "order_quantity")
    private int orderQuantity;

    @Column(name = "total_sell_price")

    private int totalSellPrice;

    @Column(name = "tax")
    private int tax;

    @Column(name = "expected_date")
    private LocalDate expectedDate;




}
