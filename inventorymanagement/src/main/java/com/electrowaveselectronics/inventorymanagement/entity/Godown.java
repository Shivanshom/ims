package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "godown")
@Getter
@Setter
@ToString

public class Godown {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "godown_id") // inventory id
    private int godownId;

    @Column(name = "location")
    private String location;

    @Column(name = "volume")
    private int volume;

    @OneToMany(
           fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "godown_id")
    private List<Product> productList;



    public Godown(){

    }

    public Godown(String location, int volume) {
        this.location = location;
        this.volume = volume;
    }


    // add convenience methods
    public void addProducts(Product tempProduct){
            if(productList==null){
                productList = new ArrayList<>();
            }
            productList.add(tempProduct);
    }


}
