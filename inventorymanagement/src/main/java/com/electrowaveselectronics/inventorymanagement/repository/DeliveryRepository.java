package com.electrowaveselectronics.inventorymanagement.repository;

import com.electrowaveselectronics.inventorymanagement.entity.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface DeliveryRepository extends JpaRepository<DeliveryOrder,Integer> {

}
