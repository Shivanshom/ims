package com.electrowaveselectronics.inventorymanagement.controller;

import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ResponseBody
    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProducts() {
        try{
            List<Object[]> products = productService.getDistinctProductsAndTotalQuantity();
            if(!products.isEmpty()){
                return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>("Product list is empty" , HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage() , HttpStatus.BAD_REQUEST);

        }
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
