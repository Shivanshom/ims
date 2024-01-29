package com.electrowaveselectronics.inventorymanagement.controller;

import com.electrowaveselectronics.inventorymanagement.entity.Godown;
import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.service.GodownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class GodownController {
    @Autowired
    private GodownService godownService;

    // godown
    @GetMapping("/api/getAllGodown")
    @ResponseBody
    public ResponseEntity<?> getAllGodown() {

        try {
            List<Godown> godowns = godownService.getAllGodown();
            if (!(godowns.isEmpty())) {
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
            Godown godown = godownService.getGodownByGodownId(godownId);
            if (!Objects.isNull(godown)) {
                return new ResponseEntity<>(godown, HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>("Godown does not exist with id: "+ godownId, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/setGodown")
    public ResponseEntity<?> setGodown(@RequestBody Godown theGodown) {

        try {
            Godown newGodown = godownService.setGodown(theGodown);
            if (!Objects.isNull(newGodown)) {
                return new ResponseEntity<>(newGodown, HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("api/deleteGodown/{godownId}")
    @ResponseBody
    public ResponseEntity<?> deleteGodownByGodownId(@PathVariable int godownId) {

        try {
            String message = godownService.deleteGodownByGodownId(godownId);
            if (!Objects.isNull(message)) {
                return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>("Something went wrong, try again...", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/getCapacity/{godownId}")
    @ResponseBody
    public ResponseEntity<?> getCapacityByGodownId(@PathVariable int godownId){
        try{
            int godownCapacity = godownService.getCapacityByGodownId(godownId);

            if (godownCapacity>0) {
                return new ResponseEntity<>("Capacity of godown "+ godownCapacity + " meters cube", HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>("Something went wrong, try again...", HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("api/updateGodown/{godownId}")
    public ResponseEntity<?> updateGodownByGodownId(@RequestBody Godown theGodown, @PathVariable int godownId) {

        try {

            Godown updatedGodown = godownService.updateGodownByGodownId(theGodown, godownId);
            return new ResponseEntity<>(updatedGodown, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // product
    @GetMapping("api/listProducts/{godownId}")
    @ResponseBody
    public ResponseEntity<?> listProductByGodownId(@PathVariable int godownId){
        try{
            List<Product> productList = godownService.listProductByGodownId(godownId);
            if(!Objects.isNull(productList)){
                return new ResponseEntity<>(productList, HttpStatus.ACCEPTED);
            }
            else{
                return new ResponseEntity<>("No products Found "+godownId, HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("api/addProduct/{godownId}")
    public ResponseEntity<?> addProductByGodownId(@RequestBody Product theproduct, @PathVariable int godownId) {
        try {
            Product newProduct = godownService.addProductByGodownId(godownId, theproduct);
            if (!Objects.isNull(newProduct)) {
                return new ResponseEntity<>("Product added "+ newProduct.getProductName(), HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>("Something went wrong, try again...", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("api/setProduct/{godownId}")
    public ResponseEntity<?> setProductByGodownId(@RequestBody Product theproduct, @PathVariable int godownId) {
        try {
            Product newProduct = godownService.setProductByGodownId(godownId, theproduct);
            if (!Objects.isNull(newProduct)) {
                return new ResponseEntity<>("Product updated "+ newProduct.getProductName(), HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>("Something went wrong, try again...", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }







}
