package com.electrowaveselectronics.inventorymanagement.controller;

import com.electrowaveselectronics.inventorymanagement.entity.Users;
import com.electrowaveselectronics.inventorymanagement.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public List<Users> findAll() throws Exception {
        return userService.findAll();
    }

    @GetMapping("/getUsers/{userId}")
    public Users getUsers(@PathVariable int userId) throws Exception {
        Users theUsers=  userService.getUser(userId);
        if (theUsers==null){
            throw new RuntimeException("User id not found= " + userId);
        }
        return theUsers;
    }

//    @PostMapping("/postusers")
//    public Users addUsers(@RequestBody Users theUsers){
//        System.out.println("----in add user");
//        Users savedUser = userService.save(theUsers);
//        System.out.println(savedUser);
//        System.out.println("----------------");
//        return savedUser;
//    }

    @PostMapping("/setusers")
    public Users addUsers(@RequestBody Users theUsers){
        theUsers.setUserId(0);
        return userService.save(theUsers);
    }

//    @PutMapping("/updateUsers")
//    public Users updateUsers(@RequestBody Users theUsers){
//        Users dbUsers=userService.save(theUsers);
//        return dbUsers;
//    }


    @PutMapping("/updateUsers")
    public ResponseEntity<?> updateUsers(@RequestBody Users theUsers ) throws Exception{
        try {
            return new ResponseEntity<>(userService.updateUsers(theUsers), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.fillInStackTrace().toString(), HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
//        if (userService.existsByUsername(request.getUsername())) {
//            return ResponseEntity.badRequest().body("Error: Username is already taken!");
//        }
//
//        if (userService.existsByEmail(request.getEmail())) {
//            return ResponseEntity.badRequest().body("Error: Email is already in use!");
//        }
//
//        User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
//        user.setRoles(Collections.singleton(Role.USER));
//        userService.save(user);
//
//        return ResponseEntity.ok("User registered successfully!");
//    }




}
