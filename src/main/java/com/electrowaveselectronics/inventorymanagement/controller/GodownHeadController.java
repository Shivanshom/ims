package com.electrowaveselectronics.inventorymanagement.controller;

import com.electrowaveselectronics.inventorymanagement.entity.GodownHead;
import com.electrowaveselectronics.inventorymanagement.service.GodownHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GodownHeadController {

    private GodownHeadService godownHeadService;
    @Autowired
    public GodownHeadController(GodownHeadService godownHeadService){
        this.godownHeadService = godownHeadService;
    }

    @GetMapping("/getAllUsers")
    public List<GodownHead> findAll() throws Exception {
        return godownHeadService.findAll();
    }

    @GetMapping("/getUsers/{userId}")
    public GodownHead getUsers(@PathVariable int userId) throws Exception {
        GodownHead theGodownHead =  godownHeadService.getUser(userId);
        if (theGodownHead ==null){
            throw new RuntimeException("User id not found= " + userId);
        }
        return theGodownHead;
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
    public GodownHead addUsers(@RequestBody GodownHead theGodownHead){
        theGodownHead.setGodownHeadId(0);
        return godownHeadService.save(theGodownHead);
    }

//    @PutMapping("/updateUsers")
//    public Users updateUsers(@RequestBody Users theUsers){
//        Users dbUsers=userService.save(theUsers);
//        return dbUsers;
//    }


    @PutMapping("/updateUsers")
    public ResponseEntity<?> updateUsers(@RequestBody GodownHead theGodownHead) throws Exception{
        try {
            return new ResponseEntity<>(godownHeadService.updateUsers(theGodownHead), HttpStatus.ACCEPTED);
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
