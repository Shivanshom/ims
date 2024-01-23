package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.entity.Supplier;
import com.electrowaveselectronics.inventorymanagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService{

    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository theSupplierRepository){supplierRepository=theSupplierRepository;}
    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier findById(int theId) {
        Optional<Supplier> result = supplierRepository.findById(theId);

        Supplier theSupplier = null;

        if(result.isPresent()){
            theSupplier=result.get();
        }else {
            // supplier not found
            throw new RuntimeException("Didn't find supplier for given purchase_id" + theId);
        }
        return theSupplier;
    }

    @Override
    public Supplier save(Supplier theSupplier) {
        return supplierRepository.save(theSupplier);
    }

    @Override
    public void deleteById(int theId) {
        // here theId is supplier id
        supplierRepository.deleteById(theId);

    }
}
