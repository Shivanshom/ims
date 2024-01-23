
package com.electrowaveselectronics.inventorymanagement.service;
import com.electrowaveselectronics.inventorymanagement.entity.DeliveryOrder;
import com.electrowaveselectronics.inventorymanagement.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
   private DeliveryRepository deliveryRepository;

    public List<DeliveryOrder> getAllDeliveryOrders() throws Exception {
        try {
            List<DeliveryOrder> deliveryOrders = deliveryRepository.findAll();
            return deliveryOrders;
        } catch (Exception e) {
            throw e;
        }
    }


    public DeliveryOrder createDeliveryOrder(DeliveryOrder deliveryOrder) throws Exception {
        try {
            return deliveryRepository.save(deliveryOrder);
        } catch (Exception e) {
            throw e;
        }
    }

    }
