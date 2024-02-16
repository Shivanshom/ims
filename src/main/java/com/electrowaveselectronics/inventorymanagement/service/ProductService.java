package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    public ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findAll() throws Exception{
        return productRepository.findAll();
    }

    public Product getProduct(int productId) throws Exception{
        try {
            Product product=productRepository.findById(productId).get();
            return product;
        }catch (Exception e){
            throw e;
        }
    }

    public Product save(Product thrproduct) {
        try {
            Product savedProduct=productRepository.save(thrproduct);
            return savedProduct;
        }catch (Exception e){
            throw e;
        }
    }

    public List<Object[]> getDistinctProductsAndTotalQuantity() {
        return productRepository.findDistinctProductsAndTotalQuantity();
    }

    public ResponseEntity<?> deleteProduct(int productId) throws Exception {
        try {
            if(productId<=0){
                return new ResponseEntity<>("ProductId must be a valid positive integer", HttpStatus.BAD_REQUEST);
            }
            Optional<Product> optional = productRepository.findById(productId);
            if (optional.isEmpty()){
                return new ResponseEntity<>("Product with given productId not Found", HttpStatus.NOT_FOUND);
            }
            Product theProduct = optional.get();
            productRepository.delete(theProduct);
            return new ResponseEntity<>("Product deleted", HttpStatus.OK);

        } catch (Exception e){
            throw e;
        }

    }
}
