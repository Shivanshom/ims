package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.repository.UserRepository;
import com.electrowaveselectronics.inventorymanagement.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    public UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
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

    public List<Users> findAll() throws Exception {
        try {
            return userRepository.findAll();
        }catch (Exception e){
            throw e;
        }

    }

    public Users getUserByUserId(int userId) throws Exception {
        try {
            Users users = userRepository.getReferenceById(userId);
            return users;
        } catch (Exception e) {
            throw e;
        }
    }
}
