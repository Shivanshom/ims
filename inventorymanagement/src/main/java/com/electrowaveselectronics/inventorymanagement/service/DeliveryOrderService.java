
package com.electrowaveselectronics.inventorymanagement.service;
import com.electrowaveselectronics.inventorymanagement.entity.DeliveryOrder;
import com.electrowaveselectronics.inventorymanagement.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DeliveryOrderService {
    @Autowired
    DeliveryRepository deliveryRepository;

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


    public DeliveryOrder createDeliveryOrder(DeliveryOrder deliveryOrder) throws Exception {
        try {
            deliveryOrder.setOrderId(0);
            return deliveryRepository.save(deliveryOrder);
        } catch (Exception e) {
            throw e;
        }
    }



    }
