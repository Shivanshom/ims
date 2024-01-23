package com.electrowaveselectronics.inventorymanagement.controller;

import com.electrowaveselectronics.inventorymanagement.entity.Godown;
import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class Controller {
    @Autowired
    private Service service;

    @GetMapping("/api/getAllGodown")
    @ResponseBody
    public ResponseEntity<?> getAllGodown() {

        try {
            List<Godown> godowns = service.getAllGodown();
            if (!(godowns.size()==0)) {
                return new ResponseEntity<>(godowns, HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>("No Godowns found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/getGodown/{godownId}")
    @ResponseBody
    public ResponseEntity<?> getGodownByGodownId(@PathVariable int godownId) {
        try {
            Godown godown = service.getGodownByGodownId(godownId);
            if (!Objects.isNull(godown)) {
                return new ResponseEntity<>(godown, HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>("Godown does not exist with id: "+ godownId, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/setGodown")
    public ResponseEntity<?> setGodown(@RequestBody Godown theGodown) {

        try {
            Godown newGodown = service.setGodown(theGodown);
            if (!Objects.isNull(newGodown)) {
                return new ResponseEntity<>(newGodown, HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("api/addProduct/{godownId}")
    public ResponseEntity<?> addProductByGodownId(@RequestBody Product theproduct, @PathVariable int godownId) {

        try {
            Product newProduct = service.addProductByGodownId(godownId, theproduct);
            if (!Objects.isNull(newProduct)) {
                return new ResponseEntity<>("Product added "+ newProduct.toString(), HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>("Something went wrong, try again...", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("api/deleteGodown/{godownId}")
    @ResponseBody
    public ResponseEntity<?> deleteGodownByGodownId(@PathVariable int godownId) {

        try {
            String message = service.deleteGodownByGodownId(godownId);
            if (!Objects.isNull(message)) {
                return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>("Something went wrong, try again...", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }






}
