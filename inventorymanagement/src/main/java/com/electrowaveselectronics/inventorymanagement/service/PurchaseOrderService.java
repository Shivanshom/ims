package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.entity.PurchaseOrder;
import com.electrowaveselectronics.inventorymanagement.entity.Supplier;
import com.electrowaveselectronics.inventorymanagement.repository.ProductRepository;
import com.electrowaveselectronics.inventorymanagement.repository.PurchaseOrderRepository;
import com.electrowaveselectronics.inventorymanagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<PurchaseOrder> getAllPurchaseOrders() throws Exception{
        return purchaseOrderRepository.findAll();
    }

    public Optional<PurchaseOrder> getPurchaseOrderByPurchaseId(int purchaseId) {
        return purchaseOrderRepository.findById(purchaseId);
    }

    public PurchaseOrder setPurchaseOrder(PurchaseOrder thepurchaseOrder) {

        thepurchaseOrder.setPurchaseDate(new Date());


        return purchaseOrderRepository.save(thepurchaseOrder);

    }

    public Optional<Supplier> getSupplierByPurchaseOrderId(int purchaseId) {
        Optional<PurchaseOrder> thePurchaseOrder = purchaseOrderRepository.findById(purchaseId);
        Optional<Supplier> supplier = supplierRepository.findById(thePurchaseOrder.get().getSupplierId());
        return supplier;

    }

    public Optional<List<Product>> getProductByPurchaseOrderId(int purchaseId) {
        Optional<PurchaseOrder> thePurchaseOrder = purchaseOrderRepository.findById(purchaseId);
        return Optional.ofNullable(thePurchaseOrder.get().getOrderedProductList());
    }
}
