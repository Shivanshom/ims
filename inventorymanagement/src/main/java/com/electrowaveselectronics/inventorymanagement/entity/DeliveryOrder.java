package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

//   FORIEGN  KEY
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name ="customer_id")
    private Customer customer;


    @Column(name = "order_quantity")
    private int orderQuantity;

    @Column(name = "total_sell_price")

    private int totalSellPrice;

    @Column(name = "tax")
    private int tax;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "expected_date")
    private Date expectedDate;



    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
            @JoinTable(
                    name = "product_delivery",
                    joinColumns = @JoinColumn(name = "order_id"),
                    inverseJoinColumns = @JoinColumn(name = "product_id")
            )

    private List<Product> products;

    public void addProduct(Product product){
        if(products.equals(null)){
            products = new ArrayList<Product>();
        }
        products.add(product);
    }

}
