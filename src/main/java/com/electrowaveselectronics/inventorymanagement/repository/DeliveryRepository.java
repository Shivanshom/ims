package com.electrowaveselectronics.inventorymanagement.repository;

import com.electrowaveselectronics.inventorymanagement.entity.DeliveryOrder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public  interface DeliveryRepository extends JpaRepository<DeliveryOrder,Integer> {

}
