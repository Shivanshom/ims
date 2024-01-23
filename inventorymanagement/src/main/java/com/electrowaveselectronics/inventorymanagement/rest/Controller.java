package com.electrowaveselectronics.inventorymanagement.rest;

import com.electrowaveselectronics.inventorymanagement.entity.Users;
import com.electrowaveselectronics.inventorymanagement.service.Servic;
import com.electrowaveselectronics.inventorymanagement.service.Service;
import com.electrowaveselectronics.inventorymanagement.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    public Servic servic;
    @GetMapping("/getusers")
    public Users getUsers(@PathVariable int userId) throws Exception {
        Users theUsers=  servic.getUser(userId);
        if (theUsers==null){
            throw new RuntimeException("User id not found= " + userId);
        }
        return theUsers;

    }
    @PostMapping("/postusers")
    public Users addUsers(@RequestBody Users theUsers){
        theUsers.setUserId(1);
        return servic.save(theUsers);
    }
}
