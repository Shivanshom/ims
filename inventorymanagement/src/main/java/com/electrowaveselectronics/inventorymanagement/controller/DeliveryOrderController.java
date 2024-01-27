package com.electrowaveselectronics.inventorymanagement.controller;
import com.electrowaveselectronics.inventorymanagement.entity.DeliveryOrder;
import com.electrowaveselectronics.inventorymanagement.service.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class DeliveryOrderController {
    @Autowired
    private DeliveryOrderService deliveryOrderService;

    @GetMapping("/getDeliveryOrders")
    @ResponseBody
    public ResponseEntity<?> getAllDeliveryOrders() {
        try {

            List<DeliveryOrder> deliveryOrders = deliveryOrderService.getAllDeliveryOrders();
            if (!deliveryOrders.isEmpty()) {
                return new ResponseEntity<>(deliveryOrders, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("No Delivery Orders found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getDeliveryOrders/{id}")
    @ResponseBody
    public ResponseEntity<?> getDeliveryOrderById(@PathVariable int id) {

        try {
            DeliveryOrder deliveryOrder = deliveryOrderService.getDeliveryOrderById(id);
            if (deliveryOrder != null) {
                return new ResponseEntity<>(deliveryOrder, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Delivery Order not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/setDeliveryOrders")
    public ResponseEntity<?> createDeliveryOrder(@RequestBody DeliveryOrder deliveryOrder) {
        try {

            DeliveryOrder newDeliveryOrder = deliveryOrderService.createDeliveryOrder(deliveryOrder);
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