package com.electrowaveselectronics.inventorymanagement.controller;

import com.electrowaveselectronics.inventorymanagement.entity.Supplier;
import com.electrowaveselectronics.inventorymanagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SupplierRestController {

    @Autowired
    private SupplierService supplierService;

    // injecting supplier using constructor injectio

    // expose "/suppliers" and return a list of suppliers

    @GetMapping("/getAllSuppliers")
    public ResponseEntity<?> getAllSuppliers()throws Exception{
        try{
            List<Supplier> suppliers = supplierService.getAllSuppliers();
            return new ResponseEntity<>(suppliers,HttpStatus.ACCEPTED);
        }catch(Exception e) {
            return  new ResponseEntity<>(e.fillInStackTrace().toString(), HttpStatus.BAD_REQUEST);
        }
    }

    // add mapping for GET /suppliers/{supplierId}
    @GetMapping("/getSupplierBySupplierId/{supplierId}")
    public ResponseEntity<?> getSupplierBySupplierId(@PathVariable int supplierId) {
        try {
            Optional<Supplier> theSupplier = supplierService.getSupplierBySupplierId(supplierId);
            return new ResponseEntity<>(theSupplier,HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.fillInStackTrace().toString(),HttpStatus.BAD_REQUEST);
        }
    }

    // add mapping for POST /suppliers - add new supplier

    @PostMapping("/setSupplier")
    public ResponseEntity<?> setSupplier(@RequestBody Supplier theSupplier){

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

//        theSupplier.setSupplierId(0);

        try {
            return new ResponseEntity<>(supplierService.setSupplier(theSupplier),HttpStatus.ACCEPTED) ;
        }catch (Exception e){
            return new ResponseEntity<>(e.fillInStackTrace().toString(),HttpStatus.NOT_FOUND);
        }






    }

    // add mapping for PUT /suppliers - update existing supplier

    @PutMapping("/updateSuppliers")
    public ResponseEntity<?> updateSuppliers(@RequestBody Supplier theSupplier) throws Exception {
try {
    return new ResponseEntity<>(supplierService.updateSuppliers(theSupplier),HttpStatus.ACCEPTED) ;
}catch (Exception e){
    return new ResponseEntity<>(e.fillInStackTrace().toString(),HttpStatus.NOT_FOUND);
}




    }




}
