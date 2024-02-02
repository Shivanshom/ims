package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.entity.GodownHead;
import com.electrowaveselectronics.inventorymanagement.repository.GodownHeadRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class LoginService {
    @Autowired
    GodownHeadRepository godownHeadRepository;

    @Autowired
    GodownHeadService godownHeadService;

    @Autowired
    AuthService authService;

    public ResponseEntity<?> login(String username, String password, HttpServletResponse response) throws Exception {
        try {
            if (username == null || password == null) {
                Map<String, String> result = new HashMap<>();
                result.put("error", "Both username and password are required.");
                return ResponseEntity.badRequest().body(result);
            }

            if (!isValidUsername(username)) {
                Map<String, String> result = new HashMap<>();
                result.put("message", "Invalid username format. " +
                        "Should not start with any special char or numeric value, " +
                        "Should not contain whitespace");
                return ResponseEntity.badRequest().body(result);
            }

            GodownHead godownHead = godownHeadRepository.findByUsername(username);

            if (godownHead != null && validatePassword(godownHead, password)) {
                // Successful login, set a cookie
                Cookie cookie = generateUserCookie(username);
                response.addCookie(cookie);

                // Create and store Auth object
                authService.createAuthInfo(username, cookie);

                Map<String, String> result = new HashMap<>();
                result.put("message", "Successfully login.");
                return ResponseEntity.accepted().body(result);
            } else {
                Map<String, String> result = new HashMap<>();
                result.put("message", "Login failed");
                return ResponseEntity.badRequest().body(result);
            }
        }
        catch (Exception e){
            throw e;
        }
    }

    private boolean isValidUsername(String username) {
        // Check for whitespace, and username not starting with a numeric value or special character
        return !Pattern.compile("\\s").matcher(username).find() &&
                !Character.isDigit(username.charAt(0)) &&
                !Pattern.compile("[^a-zA-Z0-9]").matcher(username).find();
    }

    private boolean validatePassword(GodownHead user, String password) {
        // Add your password validation logic here
        return user.getPassword().equals(password);
    }

    private Cookie generateUserCookie(String username) {
        Cookie cookie = new Cookie("user", username);
        cookie.setMaxAge(3600); // Cookie expiry time in seconds i.e. 1 hour
        return cookie;
    }

    public ResponseEntity<?> logout(HttpServletResponse response, String username) throws Exception {
        try{
            if (!username.isEmpty()) {
                Cookie cookie = new Cookie("user", null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                authService.createAuthInfo(username, cookie);

                HttpHeaders headers = new HttpHeaders();
                headers.setCacheControl(CacheControl.noStore());

                return ResponseEntity.ok("Logout successful for user: " + username);
            } else {
                return ResponseEntity.badRequest().body("User not logged in.");
            }
        }
        catch (Exception e){
            throw e;
        }
    }


    public ResponseEntity<?> register(String username, String password) {
        if (godownHeadRepository.findByUsername(username)!=null) {
            return ResponseEntity.badRequest().body("Username already taken");
        }

        GodownHead newGodownHead = godownHeadService.registerGodownHead(username, password);

        Cookie cookie = generateUserCookie(username);

        authService.createAuthInfo(newGodownHead.getUsername(), cookie);

        return ResponseEntity.ok("Registration successful");
    }
}
