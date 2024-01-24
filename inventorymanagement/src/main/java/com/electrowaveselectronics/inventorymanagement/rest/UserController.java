package com.electrowaveselectronics.inventorymanagement.rest;

import com.electrowaveselectronics.inventorymanagement.entity.Users;
import com.electrowaveselectronics.inventorymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @ResponseBody
    public ResponseEntity<?> getUserByUserId(@PathVariable int userId) {
        try {
            Users users = userService.getUserByUserId(userId);
            if (!Objects.isNull(users)) {
                return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>("Godown does not exist with id: "+ userId, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
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

//    @PostMapping("/hello")
//    public ResponseEntity<?> hello(){
//
//        return new ResponseEntity<>("hello", HttpStatus.ACCEPTED);
////        try {
////            return new
////        }catch ( Exception e){
////            throw e;
////
////        }
//    }
    @PutMapping("/updateUsers")
    public Users updateUsers(@RequestBody Users theUsers){
        Users dbUsers=userService.save(theUsers);
        return dbUsers;
    }


}
