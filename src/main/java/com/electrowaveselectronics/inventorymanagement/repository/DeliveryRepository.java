package com.electrowaveselectronics.inventorymanagement.repository;

import com.electrowaveselectronics.inventorymanagement.entity.DeliveryOrder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository

public  interface DeliveryRepository extends JpaRepository<DeliveryOrder,Integer> {
    @Query("SELECT d FROM DeliveryOrder d WHERE d.customer.customerId = :customerId")
    List<DeliveryOrder> findByCustomerCustomerId(@Param("customerId")int customerId);

   List<DeliveryOrder> findByGodownId(@Param("godownId")int godownId);

    @Query("SELECT COUNT(do) FROM DeliveryOrder do WHERE do.godownId = :godownId")
    long getTotalSalesCountByGodownID(@Param("godownId") int godownId);

    @Query("SELECT SUM(do.orderQuantity) FROM DeliveryOrder do WHERE do.godownId = :godownId")
    long getTotalProductsOrderedByGodownId(@Param("godownId") int godownId);

    @Query("SELECT COUNT(do) FROM DeliveryOrder do WHERE do.godownId = :godownId AND DATE(do.orderDate) = :date")
    long getTotalSalesOrdersByGodownIDAndDate(@Param("godownId") int godownId, @Param("date") Date date);

    @Query("SELECT SUM(do.orderQuantity) FROM DeliveryOrder do WHERE do.godownId = :godownId AND DATE(do.orderDate) = :date")
    long getTotalProductsOrderedByGodownIdAndDate(@Param("godownId") int godownId, @Param("date") Date date);
}
