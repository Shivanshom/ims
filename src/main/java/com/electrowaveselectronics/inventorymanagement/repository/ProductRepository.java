package com.electrowaveselectronics.inventorymanagement.repository;

import com.electrowaveselectronics.inventorymanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p.productName, p.godownId, SUM(p.totalQuantity) FROM Product p GROUP BY p.productName, p.godownId")
    List<Object[]> findDistinctProductsAndTotalQuantity();
    @Query("SELECT p FROM Product p WHERE p.godownId = :godownId AND p.productName = :productName")
    Product findProductByGodownIdAndProductName(@Param("godownId") int godownId, @Param("productName") String productName);

    @Query("SELECT DISTINCT p.productName FROM Product p")
    List<String> findDistinctProductNames();

}
