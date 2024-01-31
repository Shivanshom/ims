package com.electrowaveselectronics.inventorymanagement.controller;

import com.electrowaveselectronics.inventorymanagement.dto.PurchaseOrderDTO;
import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.entity.PurchaseOrder;
import com.electrowaveselectronics.inventorymanagement.entity.Supplier;
import com.electrowaveselectronics.inventorymanagement.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PurchaseOrderRestController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("/getAllPurchaseOrders")
    public ResponseEntity<?> getAllPurchaseOrders() throws Exception {
        try {
            List<PurchaseOrder> purchaseOrders = purchaseOrderService.getAllPurchaseOrders();
            return new ResponseEntity<>(purchaseOrders, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.fillInStackTrace().toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getPurchaseOrderByPurchaseId/{purchaseId}")
    public ResponseEntity<?> getPurchaseOrderByPurchaseId(@PathVariable int purchaseId) {
        try {
            Optional<PurchaseOrder> thepurchaseOrder = purchaseOrderService.getPurchaseOrderByPurchaseId(purchaseId);
            return new ResponseEntity<>(thepurchaseOrder, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.fillInStackTrace().toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createPurchaseOrder")
    public ResponseEntity<?> setPurchaseOrder(@RequestBody PurchaseOrderDTO thepurchaseOrderDTO) {
        try {
            return new ResponseEntity<>(purchaseOrderService.createPurchaseOrder(thepurchaseOrderDTO), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.fillInStackTrace().toString(), HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/createPurchaseOrder")
//    public ResponseEntity<?> createPurchaseOrder(@RequestBody PurchaseOrder thepurchaseOrderDTO) {
//        try {
//
//            return new ResponseEntity<>(purchaseOrderService.createPurchaseOrder(thepurchaseOrderDTO), HttpStatus.ACCEPTED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.fillInStackTrace().toString(), HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/getSupplierByPurchaseId/{purchaseId}")
    public ResponseEntity<?> getSupplierByPurchaseOrderId(@PathVariable int purchaseId) {
        try {
            Optional<Supplier> theSupplier = purchaseOrderService.getSupplierByPurchaseOrderId(purchaseId);
            return new ResponseEntity<>(theSupplier, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.fillInStackTrace().toString(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/getProductByPurchaseOrderId/{purchaseId}")
//    public ResponseEntity<?> getProductByPurchaseOrderId(@PathVariable int purchaseId) {
//        try {
//            Optional<List<Product>> products = purchaseOrderService.getProductByPurchaseOrderId(purchaseId);
//            return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.fillInStackTrace().toString(), HttpStatus.BAD_REQUEST);
//        }
//    }


}
