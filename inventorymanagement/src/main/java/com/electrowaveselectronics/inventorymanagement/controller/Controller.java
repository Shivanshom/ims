package com.electrowaveselectronics.inventorymanagement.controller;
import com.electrowaveselectronics.inventorymanagement.entity.DeliveryOrder;
import com.electrowaveselectronics.inventorymanagement.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class Controller {
    @Autowired
    private Service service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllDeliveryOrders() {
        try {

            List<DeliveryOrder> deliveryOrders = service.getAllDeliveryOrders();
            if (!deliveryOrders.isEmpty()) {
                return new ResponseEntity<>(deliveryOrders, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("No Delivery Orders found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createDeliveryOrder(@RequestBody DeliveryOrder deliveryOrder) {
        try {

            DeliveryOrder newDeliveryOrder = service.createDeliveryOrder(deliveryOrder);
            if (!Objects.isNull(newDeliveryOrder)) {
                return new ResponseEntity<>(newDeliveryOrder, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to create Delivery Order", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}