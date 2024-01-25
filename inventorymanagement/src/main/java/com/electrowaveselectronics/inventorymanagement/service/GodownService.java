package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.entity.Godown;
import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.repository.GodownRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.RuntimeErrorException;
import java.util.*;

@org.springframework.stereotype.Service
public class GodownService {


    @Autowired
    private GodownRepository godownRepository;

    public List<Godown> getAllGodown() throws Exception{
        try {
            List<Godown> godowns = godownRepository.findAll();
            return godowns;
        }catch (Exception e){
            throw e;
        }
    }

    public Godown setGodown(Godown theGodown) throws Exception {
        try {
            theGodown.setGodownId(0);
            return godownRepository.save(theGodown);
        }catch (Exception e){
            throw e;
        }

    }

    public Godown getGodownByGodownId(int godownId) throws Exception {
        try {
            Optional<Godown> result = godownRepository.findById(godownId);
            return result.get();
        }catch (Exception e){
            throw e;
        }

    }

    public String deleteGodownByGodownId(int godownId)  throws Exception{
        try {
            Godown tempGodown = godownRepository.findById(godownId).get();
            godownRepository.deleteById(godownId);
            return "Godown Deleted with id: "+ godownId;

        }catch (Exception e){
            throw e;
        }

    }

    public int updateGodownVolumeByGodownId(int Volume, int godownId) throws Exception {
        try {
            Godown existingGodown = godownRepository.findById(godownId).get();
            if(Volume>0){
                existingGodown.setVolume(Volume);
                godownRepository.save(existingGodown);
                return existingGodown.getVolume();
            }
            else throw new RuntimeException("Value not in valid range.");


        } catch (Exception e) {
            throw e;
        }
    }


    public int getCapacityByGodownId(int godownId)  throws Exception{
        try {
            Godown tempGodown = godownRepository.findById(godownId).get();
            List<Product> productList = tempGodown.getProductList();
            int capacity=0;
            for(Product product : productList){
                capacity+=product.getProductVolume()* product.getTotalQuantity();
            }
            return (tempGodown.getVolume() - capacity);

        }catch (Exception e){
            throw e;
        }

    }

    // Product
    public Product addProductByGodownId(int godownId, Product theProduct) throws Exception {
        try {
            Godown tempGodown = godownRepository.findById(godownId).get();
            Product existingProduct = tempGodown.findProductByProductName(theProduct.getProductName());
            if(existingProduct==null){
                tempGodown.addProducts(theProduct);
                existingProduct=theProduct;
            }else{
                int newTotalQuantity = existingProduct.getTotalQuantity()+ theProduct.getTotalQuantity();
                existingProduct.setTotalQuantity(newTotalQuantity);
            }

            godownRepository.save(tempGodown);
            return existingProduct;

        }catch (Exception e){
            throw e;
        }

    }

    public Product setProductByGodownId(int godownId, Product theProduct) throws Exception{
        try {
            Godown tempGodown = godownRepository.findById(godownId).get();
            Product existingProduct = tempGodown.findProductByProductName(theProduct.getProductName());
            if(existingProduct==null){
                throw new EntityNotFoundException("Product not found in godown id: "+godownId);
            }
            if(theProduct.getProductVolume()>0){
                existingProduct.setProductVolume(theProduct.getProductVolume());
            }
            if(theProduct.getPrice()>0){
                existingProduct.setPrice(theProduct.getPrice());
            }
            if(theProduct.getTotalQuantity()>0){
                existingProduct.setTotalQuantity(theProduct.getTotalQuantity());
            }
            godownRepository.save(tempGodown);
            return existingProduct;
        }catch (Exception e){
            throw e;

        }
    }


}
