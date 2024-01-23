package com.electrowaveselectronics.inventorymanagement.repository;

import com.electrowaveselectronics.inventorymanagement.entity.Godown;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GodownRepository extends JpaRepository<Godown, Integer> {
}
