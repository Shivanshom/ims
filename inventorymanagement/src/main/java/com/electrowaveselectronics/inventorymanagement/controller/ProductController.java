package com.electrowaveselectronics.inventorymanagement.controller;

import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.entity.Users;
import com.electrowaveselectronics.inventorymanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProduct")
    public List<Product> findAll() throws Exception {
        return productService.findAll();
    }

    @GetMapping("/getProduct/{productId}")
    public Product getProduct(@PathVariable int productId) throws Exception {
        Product theProduct=  productService.getProduct(productId);
        if (theProduct==null){
            throw new RuntimeException("Product id not found= " + productId);
        }
        return theProduct;
    }


    @PostMapping("/setProduct")
    public Product addProduct(@RequestBody Product thrproduct){
        thrproduct.setProductId(0);
        return productService.save(thrproduct);
    }
}
