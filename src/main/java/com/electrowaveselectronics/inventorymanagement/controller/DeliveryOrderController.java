package com.electrowaveselectronics.inventorymanagement.controller;
import com.electrowaveselectronics.inventorymanagement.dto.DeliveryOrderDTO;
import com.electrowaveselectronics.inventorymanagement.entity.DeliveryOrder;
import com.electrowaveselectronics.inventorymanagement.service.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class DeliveryOrderController {
    @Autowired
    private DeliveryOrderService deliveryOrderService;
    //FOR ADMIN USE
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

    @GetMapping("/getDeliveryOrdersById/{id}")
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

    // FOR CUSTOMER USE
    @PostMapping("/placeOrder/{customerId}")
    public ResponseEntity<?> setOrder(@PathVariable int customerId, @RequestBody DeliveryOrderDTO deliveryOrderDTO) {
        try {

            DeliveryOrder newDeliveryOrder = deliveryOrderService.setOrder(customerId, deliveryOrderDTO);
            if (!Objects.isNull(newDeliveryOrder)) {
                return new ResponseEntity<>("ORDER HAS BEEN PLACED SUCESSFULLY", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to FULFILL the Order", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("FAILED TO PLACE ORDER", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/placedOrderDetails/{customerId}")
    public ResponseEntity<?> orderDetails(@PathVariable int customerId) {
        try {

            List<DeliveryOrder> newDeliveryOrder = deliveryOrderService.getDeliveryOrderByCustomerId(customerId);

            if (!newDeliveryOrder.isEmpty()) { // Corrected from !newDeliveryOrder.isEmpty()
                return new ResponseEntity<>(newDeliveryOrder, HttpStatus.OK); // Return the actual object inside Optional
            }
            else {
                return new ResponseEntity<>("YOU DIDN'T PLACED ANY ORDER", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("FAILED TO FETCH ORDER DETAILS", HttpStatus.BAD_REQUEST);
        }
    }





}