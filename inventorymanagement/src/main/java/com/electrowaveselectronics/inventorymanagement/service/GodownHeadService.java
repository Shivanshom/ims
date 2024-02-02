package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.repository.GodownHeadRepository;
import com.electrowaveselectronics.inventorymanagement.entity.GodownHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GodownHeadService {


    public GodownHeadRepository godownHeadRepository;

    @Autowired
    public GodownHeadService(GodownHeadRepository godownHeadRepository) {
        this.godownHeadRepository = godownHeadRepository;
    }


    public GodownHead save(GodownHead theGodownHead) {
        try {
            GodownHead savedUser = godownHeadRepository.save(theGodownHead);
            return savedUser;
        } catch (Exception e){
            throw e;
        }

    }

    public GodownHead getUser(int userId) throws Exception {
        try{
            GodownHead u = godownHeadRepository.findById(userId).get();
            return u;
        }catch (Exception e){
            throw e;
        }
    }

    public List<GodownHead> findAll() throws Exception {
        try {
            return godownHeadRepository.findAll();
        }catch (Exception e){
            throw e;
        }

    }

    public GodownHead getUserByUserId(int userId) throws Exception {
        try {
            Optional<GodownHead> users = godownHeadRepository.findById(userId);
            return users.get();
        } catch (Exception e) {
            throw e;
        }
    }

    public GodownHead updateUsers(GodownHead theGodownHead) {
        try {
            Optional<Integer> optional=Optional.of(theGodownHead.getGodownHeadId());
            if (optional.isEmpty()){
                throw new Exception("User id is not provided in input, please try again");
            }
            GodownHead existingGodownHead = godownHeadRepository.findById(theGodownHead.getGodownHeadId()).orElseThrow(()-> new Exception("User not found for provided id"));

            if (theGodownHead.getGodownHeadName()!=null){
                existingGodownHead.setGodownHeadName(theGodownHead.getGodownHeadName());
            }

            if (theGodownHead.getPassword()!=null){
                existingGodownHead.setPassword(theGodownHead.getPassword());
            }

            if (theGodownHead.getRole()!=null){
                existingGodownHead.setRole(theGodownHead.getRole());
            }

            return setUsers(existingGodownHead);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public GodownHead setUsers(GodownHead theGodownHead) {
        try {
            return godownHeadRepository.save(theGodownHead);
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
