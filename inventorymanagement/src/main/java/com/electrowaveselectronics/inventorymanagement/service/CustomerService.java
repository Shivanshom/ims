package com.electrowaveselectronics.inventorymanagement.service;
import com.electrowaveselectronics.inventorymanagement.entity.Customer;
import com.electrowaveselectronics.inventorymanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    public CustomerRepository customerRepository;



    public List<Customer> getAllCustomers() throws Exception {
        try {
            List<Customer> customers = customerRepository.findAll();
            return customers;
        } catch (Exception e) {
            throw e;
        }
    }
    @Transactional
    public Customer createCustomer(Customer customer) throws Exception {
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw e;
        }
    }

    public Customer getCustomerById(int customerId) throws Exception {
        try {
            Customer customer = customerRepository.findById(customerId).get();
            return customer; // Make sure to return the customer object
        } catch (Exception e) {
            throw e;
        }
    }


    public Customer updateCustomer(int customerId, Customer newDataCustomer) throws Exception {
        try {
            Customer existingCustomer = getCustomerById(customerId);
            existingCustomer.setCustomerName(newDataCustomer.getCustomerName());
            existingCustomer.setCustomerAddress(newDataCustomer.getCustomerAddress());
            existingCustomer.setCustomerNo(newDataCustomer.getCustomerNo());
            return customerRepository.save(existingCustomer);

        }catch(Exception e){
            throw e;

        }

    }
}