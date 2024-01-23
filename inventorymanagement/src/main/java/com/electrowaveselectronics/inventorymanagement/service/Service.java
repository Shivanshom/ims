package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.entity.Users;

import java.util.List;

public interface Service {

    List<Users> findAll();

    Users findById(int theId);

    Users save(Users theUsers);

    void deleteById(int theId);
}
