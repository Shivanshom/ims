package com.electrowaveselectronics.inventorymanagement.service;
import com.electrowaveselectronics.inventorymanagement.entity.Godown;
import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.repository.GodownRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    GodownRepository godownRepository;

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
            if(!Objects.isNull(tempGodown)){
                godownRepository.deleteById(godownId);
                return "Godown Deleted with id: "+ godownId;
            }
            return "Godown not found with id: "+godownId;

        }catch (Exception e){
            throw e;
        }

    }

    public Product addProductByGodownId(int godownId, Product theProduct) throws Exception {
        try {
            Godown tempGodown = godownRepository.findById(godownId).get();
            tempGodown.addProducts(theProduct);
            godownRepository.save(tempGodown);
            return theProduct;

        }catch (Exception e){
            throw e;
        }

    }
}
