package com.electrowaveselectronics.inventorymanagement.controller;

import com.electrowaveselectronics.inventorymanagement.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/api/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest, HttpServletResponse response) {
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");
            return loginService.login(username, password, response);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/logout")
    public ResponseEntity<?> logout(HttpServletResponse response, @CookieValue(value = "user", defaultValue = "") String username) {
        try {
            return loginService.logout(response, username);
        }
        catch (Exception e){
            return new ResponseEntity<>("Somehing went wrong ... "+ e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String password = request.get("password");
            return loginService.register(username, password);
        }
        catch (Exception e){
            return new ResponseEntity<>("Somehing went wrong ... "+ e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
