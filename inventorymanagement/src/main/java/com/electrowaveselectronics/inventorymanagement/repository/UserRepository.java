package com.electrowaveselectronics.inventorymanagement.repository;

import com.electrowaveselectronics.inventorymanagement.entity.Users;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}
