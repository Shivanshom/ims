package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.repository.UserRepository;
import com.electrowaveselectronics.inventorymanagement.entity.Users;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Service
@NoArgsConstructor
public class ServiceImpl implements Service{

    private UserRepository userRepository;
    @Autowired
    public ServiceImpl(UserRepository theUserRepository){
        userRepository=theUserRepository;
    }
    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findById(int theId) {
        Optional<Users> result= userRepository.findById(theId);

        Users theUsers=null;
        if (result.isPresent()){
            theUsers=result.get();
        }
        else {
            throw new RuntimeException("User id not found- " + theId);
        }
        return theUsers;
    }

    @Override
    public Users save(Users theUsers) {
        return userRepository.save(theUsers);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);

    }
}
