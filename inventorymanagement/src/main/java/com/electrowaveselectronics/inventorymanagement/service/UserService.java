package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.repository.UserRepository;
import com.electrowaveselectronics.inventorymanagement.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            Users u = userRepository.findById(userId).get();
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
            Optional<Users> users = userRepository.findById(userId);
            return users.get();
        } catch (Exception e) {
            throw e;
        }
    }

    public Users updateUsers(Users theUsers) {
        try {
            Optional<Integer> optional=Optional.of(theUsers.getUserId());
            if (optional.isEmpty()){
                throw new Exception("User id is not provided in input, please try again");
            }
            Users existingUsers=userRepository.findById(theUsers.getUserId()).orElseThrow(()-> new Exception("User not found for provided id"));

            if (theUsers.getUserName()!=null){
                existingUsers.setUserName(theUsers.getUserName());
            }

            if (theUsers.getPassword()!=null){
                existingUsers.setPassword(theUsers.getPassword());
            }

            if (theUsers.getRole()!=null){
                existingUsers.setRole(theUsers.getRole());
            }

            return setUsers(existingUsers);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Users setUsers(Users theUsers) {
        try {
            return userRepository.save(theUsers);
        }catch (Exception e){
            throw e;
        }
    }

//    public Optional<Users> getUserByUserId(int userId){
//        try {
//            return userRepository.findById(userId);
//        }catch (Exception e){
//            throw e;
//        }
//    }
}
