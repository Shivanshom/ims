package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.repository.UserRepository;
import com.electrowaveselectronics.inventorymanagement.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servic {


    public UserRepository userRepository;

    @Autowired
    public Servic(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users save(Users theUsers) {
        try {
            Users savedUser = userRepository.save(theUsers);
            return savedUser;
        } catch (Exception e){
            throw e;
        }

    }

    public Users getUser(int userId) throws Exception {
        try{
            Users u = userRepository.getReferenceById(userId);
            return u;
        }catch (Exception e){
            throw e;
        }
    }
}
