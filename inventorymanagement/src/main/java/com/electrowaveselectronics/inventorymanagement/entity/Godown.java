package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "godown")
@Data
public class Godown {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "godown_id") // inventory id
    private int godownId;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "volume")
    private int volume;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "godown_id")
    private List<Product> productList;



    public Godown(){
    }

    public Godown(String city, String state, int volume) {
        this.city = city;
        this.state = state;
        this.volume = volume;
    }

    // add convenience methods
    public void addProducts(Product tempProduct){
            if(productList==null){
                productList = new ArrayList<>();
            }
            productList.add(tempProduct);
    }

    public Product findProductByProductName(String productName){
        for (Product product: productList){
            if(Objects.equals(product.getProductName(), productName)){
                return product;
            }
        }
        return null;
    }


}
