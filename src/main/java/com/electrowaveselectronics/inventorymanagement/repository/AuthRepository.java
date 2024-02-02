package com.electrowaveselectronics.inventorymanagement.repository;

import com.electrowaveselectronics.inventorymanagement.entity.Auth;
import com.electrowaveselectronics.inventorymanagement.entity.GodownHead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;

public interface AuthRepository extends JpaRepository<Auth,Integer> {
    Auth findByUsername(String username);
}
