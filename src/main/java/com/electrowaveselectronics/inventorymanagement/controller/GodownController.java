package com.electrowaveselectronics.inventorymanagement.controller;

import com.electrowaveselectronics.inventorymanagement.entity.Auth;
import com.electrowaveselectronics.inventorymanagement.entity.AuthHolder;
import com.electrowaveselectronics.inventorymanagement.entity.Godown;
import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.service.GodownHeadService;
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

    @Autowired
    private GodownHeadService godownHeadService;

    // godown
    @GetMapping("/api/getAllGodown")
    @ResponseBody
    public ResponseEntity<?> getAllGodown(@CookieValue(name = "user", defaultValue = "") String username) {

        try {
            if (!username.isEmpty() && "admin".equals(godownHeadService.getRoleByUsername(username).name())) {
                return godownService.getAllGodown();
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/getGodown/{godownId}")
    @ResponseBody
    public ResponseEntity<?> getGodownByGodownId(@PathVariable String godownId, @CookieValue(name = "user", defaultValue = "") String username) {
        try {
            if (!username.isEmpty()) {
                int parsedGodownId = Integer.parseInt(godownId);
                return godownService.getGodownByGodownId(parsedGodownId);
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        }
        catch (NumberFormatException e) {
            String errorMessage = "Invalid Godown ID format. Please provide a valid integer.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/setGodown")
    public ResponseEntity<?> setGodown(@RequestBody Godown theGodown, @CookieValue(name = "user", defaultValue = "") String username) {

        try {
            if (!username.isEmpty()) {
                ResponseEntity<?> responseEntity = godownService.setGodown(theGodown);
                return new ResponseEntity<>("Operation successful: " + responseEntity.getBody(), responseEntity.getStatusCode());
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/createGodown")
    public ResponseEntity<?> createGodown(@RequestBody Godown theGodown, @CookieValue(name = "user", defaultValue = "") String username) {

        try {
            if (!username.isEmpty()) {
                ResponseEntity<?> responseEntity = godownService.createGodown(theGodown);
                return new ResponseEntity<>("Operation successful: " + responseEntity.getBody(), responseEntity.getStatusCode());
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("api/deleteGodown/{godownId}")
    @ResponseBody
    public ResponseEntity<?> deleteGodownByGodownId(@PathVariable String godownId, @CookieValue(name = "user", defaultValue = "") String username) {

        try {
            if (!username.isEmpty()) {
                int parsedGodownId = Integer.parseInt(godownId);
                return godownService.deleteGodownByGodownId(parsedGodownId);
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        }
        catch (NumberFormatException e) {
            String errorMessage = "Invalid Godown ID format. Please provide a valid integer.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/getCapacity/{godownId}")
    @ResponseBody
    public ResponseEntity<?> getCapacityByGodownId(@PathVariable String godownId, @CookieValue(name = "user", defaultValue = "") String username){
        try{
            if (!username.isEmpty()) {
                int parsedGodownId = Integer.parseInt(godownId);
                int godownCapacity = godownService.getCapacityByGodownId(parsedGodownId);
                if (godownCapacity>=0) {
                    return new ResponseEntity<>( godownCapacity, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Godown Capacity Can't be negative...", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }

        }
        catch (NumberFormatException e) {
            String errorMessage = "Invalid Godown ID format. Please provide a valid integer.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("api/updateGodown/{godownId}")
    public ResponseEntity<?> updateGodownByGodownId(@RequestBody Godown theGodown, @PathVariable String godownId, @CookieValue(name = "user", defaultValue = "") String username) {

        try {
            if (!username.isEmpty()) {
                int parsedGodownId = Integer.parseInt(godownId);
                return godownService.updateGodownByGodownId(theGodown, parsedGodownId);
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }

        }
        catch (NumberFormatException e) {
            String errorMessage = "Invalid Godown ID format. Please provide a valid integer.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // product
    @GetMapping("api/listProducts/{godownId}")
    @ResponseBody
    public ResponseEntity<?> listProductByGodownId(@PathVariable String godownId, @CookieValue(name = "user", defaultValue = "") String username){
        try{
            if (!username.isEmpty()) {
                int parsedGodownId = Integer.parseInt(godownId);
                return godownService.listProductByGodownId(parsedGodownId);
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        }
        catch (NumberFormatException e) {
            String errorMessage = "Invalid Godown ID format. Please provide a valid integer.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        }
        catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("api/addProduct")
    public ResponseEntity<?> addProductByGodownId(@RequestBody Product theproduct, @CookieValue(name = "user", defaultValue = "") String username) {
        try {
            if (!username.isEmpty()) {
                return godownService.addProductByGodownId(theproduct);
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("api/updateProduct")
    public ResponseEntity<?> updateProductByGodownId(@RequestBody Product theproduct, @CookieValue(name = "user", defaultValue = "") String username) {
        try {
            if (!username.isEmpty()) {
                return godownService.updateProductByGodownId(theproduct);
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/findGodownsByAddress")
    public ResponseEntity<?> findGodownsByAddress(@RequestParam String partialAddress, @CookieValue(name = "user", defaultValue = "") String username) {
        try {
            if (!username.isEmpty()) {
                List<Godown> godowns = godownService.findGodownsByAddress(partialAddress);

                return godowns.isEmpty()
                        ? new ResponseEntity<>("No Godowns found for the provided address", HttpStatus.NOT_FOUND)
                        : new ResponseEntity<>(godowns, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Access denied. Please login.", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
