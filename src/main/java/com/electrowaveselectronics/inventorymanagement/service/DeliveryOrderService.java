
package com.electrowaveselectronics.inventorymanagement.service;
import com.electrowaveselectronics.inventorymanagement.entity.Customer;
import com.electrowaveselectronics.inventorymanagement.entity.DeliveryOrder;
import com.electrowaveselectronics.inventorymanagement.repository.CustomerRepository;
import com.electrowaveselectronics.inventorymanagement.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class DeliveryOrderService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    CustomerRepository customerRepository;


    public List<DeliveryOrder> getAllDeliveryOrders() throws Exception {
        try {
            List<DeliveryOrder> deliveryOrders = deliveryRepository.findAll();

            return deliveryOrders;
        } catch (Exception e) {
            throw e;
        }
    }

    public DeliveryOrder  getDeliveryOrderById(int id) throws Exception {

        try {
            Optional<DeliveryOrder> deliveryOrderById = deliveryRepository.findById(id);
            return deliveryOrderById.orElse(null);
        } catch (Exception e) {
            throw e;
        }
    }


    public DeliveryOrder createDeliveryOrder(int customerId, @RequestBody DeliveryOrder deliveryOrder) throws Exception {
        try {

            Customer customer = customerRepository.findById(customerId).get();
            deliveryOrder.setOrderDate(new Date());
            // FOR SETTING DELIVERY DATE
            int deliveryDays = 7;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, deliveryDays);
            deliveryOrder.setExpectedDate( calendar.getTime());

            deliveryOrder.setCustomer(customer);
            return deliveryRepository.save(deliveryOrder);
        } catch (Exception e) {
            throw e;
        }
    }



    }
