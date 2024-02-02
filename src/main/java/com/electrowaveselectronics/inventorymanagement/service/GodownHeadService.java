package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.entity.Auth;
import com.electrowaveselectronics.inventorymanagement.repository.AuthRepository;
import com.electrowaveselectronics.inventorymanagement.repository.GodownHeadRepository;
import com.electrowaveselectronics.inventorymanagement.entity.GodownHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class GodownHeadService {

    @Autowired
    public AuthRepository authRepository;


    public GodownHeadRepository godownHeadRepository;

    @Autowired
    public GodownHeadService(GodownHeadRepository godownHeadRepository) {
        this.godownHeadRepository = godownHeadRepository;
    }


    public GodownHead save(GodownHead theGodownHead) {
        try {
            GodownHead savedGodownHead = godownHeadRepository.save(theGodownHead);
            return savedGodownHead;
        } catch (Exception e){
            throw e;
        }

    }

    public GodownHead getGodownHead(int GodownHeadId) throws Exception {
        try {
            GodownHead u = godownHeadRepository.findById(GodownHeadId).get();
            return u;
        } catch (Exception e) {
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

    public GodownHead getGodownHeadByGodownHeadId(int GodownHeadId) throws Exception {
        try {
            Optional<GodownHead> GodownHead = godownHeadRepository.findById(GodownHeadId);
            return GodownHead.get();
        } catch (Exception e) {
            throw e;
        }
    }

    public GodownHead updateGodownHead(GodownHead theGodownHead) {
        try {
            Optional<Integer> optional = Optional.of(theGodownHead.getGodownHeadId());
            if (optional.isEmpty()) {
                throw new Exception("GodownHead id is not provided in input, please try again");
            }
            GodownHead existingGodownHead = godownHeadRepository.findById(theGodownHead.getGodownHeadId()).orElseThrow(() -> new Exception("GodownHead not found for provided id"));

            if (theGodownHead.getGodownHeadName() != null) {
                existingGodownHead.setGodownHeadName(theGodownHead.getGodownHeadName());
            }

            if (theGodownHead.getPassword() != null) {
                existingGodownHead.setPassword(theGodownHead.getPassword());
            }

            if (theGodownHead.getRole() != null) {
                existingGodownHead.setRole(theGodownHead.getRole());
            }

            return setGodownHead(existingGodownHead);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GodownHead setGodownHead(GodownHead theGodownHead) {
        try {
            return godownHeadRepository.save(theGodownHead);
        } catch (Exception e) {
            throw e;
        }
    }

    public HashMap<String, String> loginwithPassword(String username, String password) {
        HashMap<String, String> result = new HashMap<>();
        try {
            GodownHead godownHead = godownHeadRepository.findByUsername(username);
            if (godownHead != null && godownHead.getPassword().equals(password)) {
                result.put("success", "Successfully logged in");
            } else {
                result.put("error", "Invalid username or password");
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }


    public void setAuthToken(String token, String username){
        Auth auth = authRepository.findByUsername(username);
        if (auth != null) {
            auth.setToken(token);
            authRepository.save(auth);
        }


    }
}
