package com.electrowaveselectronics.inventorymanagement.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "godown")
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

    public int getGodownId() {
        return godownId;
    }

    public void setGodownId(int godownId) {
        this.godownId = godownId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Godown{" +
                "godownId=" + godownId +
                ", location='" + location + '\'' +
                ", volume=" + volume +
                ", productList=" + productList +
                '}';
    }

    // add convenience methods
    public void addProducts(Product tempProduct){
            if(productList==null){
                productList = new ArrayList<>();
            }
            productList.add(tempProduct);
    }


}
