package com.electrowaveselectronics.inventorymanagement.repository;

import com.electrowaveselectronics.inventorymanagement.entity.GodownHead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GodownHeadRepository extends JpaRepository<GodownHead, Integer> {
    GodownHead findByGodownHeadName(String godownHeadName);
    GodownHead findByUsername(String username);
}
