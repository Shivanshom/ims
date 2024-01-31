package com.electrowaveselectronics.inventorymanagement.controller;

import com.electrowaveselectronics.inventorymanagement.entity.Customer;
import com.electrowaveselectronics.inventorymanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Objects;

@RestController
// Adjusted base path
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;



    @GetMapping("/getAllCustomers")
    @ResponseBody
    public ResponseEntity<?> getAllCustomers() {

        try {
            List<Customer> customers = customerService.getAllCustomers();
            if (!customers.isEmpty()) {
                return new ResponseEntity<>(customers, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Customer list is empty", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {

        try {
            Customer newCustomer = customerService.createCustomer(customer);
            if (!Objects.isNull(newCustomer)) {
                return new ResponseEntity<>(newCustomer, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Something went wrong while creating the customer", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id) {

        try {
            Customer customer = customerService.getCustomerById(id);
            if (customer != null) {
                return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Customer not found with ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateCustomerById/{customerId}")
    public Customer updateCustomerById(@PathVariable int customerId, @RequestBody Customer NewDataCustomer) throws Exception {
        Customer customer = customerService.updateCustomer(customerId, NewDataCustomer);
        return customer;
    }




}
