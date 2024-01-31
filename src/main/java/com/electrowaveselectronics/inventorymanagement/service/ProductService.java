package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
