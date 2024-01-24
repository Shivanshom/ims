package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.entity.Supplier;
import com.electrowaveselectronics.inventorymanagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;


    public List<Supplier> getAllSuppliers()throws Exception {
        try{
            return supplierRepository.findAll();
        }catch (Exception e){
            throw e;
        }
    }

    public Supplier updateSuppliers(Supplier theSupplier) {
        try {
            Optional<Integer> optional = Optional.of(theSupplier.getSupplierId());
            if (optional.isEmpty()) {
                throw new Exception("Supplier id not provided in input, please try again");
            }

            Supplier existingSupplier = supplierRepository.findById(theSupplier.getSupplierId()).orElseThrow(()-> new Exception("Supplier not found for provided id"));

            if (theSupplier.getSupplierName() != null) {
                existingSupplier.setSupplierName(theSupplier.getSupplierName());
            }
            if (theSupplier.getContactNumber() != null) {
                existingSupplier.setContactNumber(theSupplier.getContactNumber());
            }
            if (theSupplier.getAddress() != null) {
                existingSupplier.setAddress(theSupplier.getAddress());
            }
            

            return setSupplier(existingSupplier);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Supplier setSupplier(Supplier theSupplier) {
        try {
            return supplierRepository.save(theSupplier);
        }catch (Exception e){
            throw e;
        }
    }

    public Optional<Supplier> getSupplierBySupplierId(int supplierId) {
        try {
            return supplierRepository.findById(supplierId);
        } catch (Exception e) {
            throw e;
        }

    }
}
