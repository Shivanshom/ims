package com.electrowaveselectronics.inventorymanagement.rest;

import com.electrowaveselectronics.inventorymanagement.entity.Supplier;
import com.electrowaveselectronics.inventorymanagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SupplierRestController {

    private SupplierService supplierService;

    // injecting supplier using constructor injection

    @Autowired
    public SupplierRestController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // expose "/suppliers" anfd return a list of suppliers

    @GetMapping("/suppliers")
    public List<Supplier> findAll(){return supplierService.findAll();    }

    // add mapping for GET /suppliers/{supplierId}
    @GetMapping("/suppliers/{supplierId}")
    public Supplier getSupplier(@PathVariable int supplierId) {
        Supplier theSupplier = supplierService.findById(supplierId);

        if(theSupplier==null){
            throw new RuntimeException("Supplier id not fount - "+supplierId);
        }

        return theSupplier;
    }

    // add mapping for POST /suppliers - add new supplier

    @PostMapping("/suppliers")
    public Supplier addSupplier(@RequestBody Supplier theSupplier){

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theSupplier.setSupplierId(0);

        return supplierService.save(theSupplier);
    }

    // add mapping for PUT /suppliers - update existing supplier

    @PutMapping("/suppliers")
    public Supplier updateSupplier(@RequestBody Supplier theSupplier){

        Supplier dbSupplier = supplierService.save(theSupplier);

        return dbSupplier;
    }

    @DeleteMapping("/suppliers/{supplierId}")
    public String deleteSupplier(@PathVariable int supplierId) {

        Supplier tempSupplier = supplierService.findById(supplierId);

        // throw exception if null

        if(tempSupplier == null) {
            throw new RuntimeException("Supplier id not found - " + supplierId);

        }

        supplierService.deleteById(supplierId);

        return "Deleted employee id - " + supplierId;
    }


}
