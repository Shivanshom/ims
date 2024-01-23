package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.entity.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> findAll();

    Supplier findById(int theId);

    Supplier save(Supplier theSupplier);

    void deleteById(int theId);
}
