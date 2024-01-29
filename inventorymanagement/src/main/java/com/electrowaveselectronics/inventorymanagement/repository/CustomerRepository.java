package com.electrowaveselectronics.inventorymanagement.repository;

import com.electrowaveselectronics.inventorymanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
