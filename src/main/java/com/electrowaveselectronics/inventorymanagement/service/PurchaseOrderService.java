package com.electrowaveselectronics.inventorymanagement.service;

import com.electrowaveselectronics.inventorymanagement.dto.PurchaseOrderDTO;
import com.electrowaveselectronics.inventorymanagement.dto.PurchaseProductDTO;
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






    public String createPurchaseOrder(PurchaseOrderDTO thepurchaseOrderDTO) {
        try {
            if ((thepurchaseOrderDTO.getSupplierId() <= 0) || (thepurchaseOrderDTO.getGodownId() <= 0)) {
                throw new IllegalArgumentException("Supplier ID and Godown ID must be provided.");
            }

            Optional<Supplier> supplierOptional = supplierRepository.findById(thepurchaseOrderDTO.getSupplierId());
            if (supplierOptional.isEmpty()) {
                throw new IllegalArgumentException("Supplier with the provided ID does not exist.");
            }
            Supplier supplier = supplierOptional.get();
            PurchaseOrder thepurchaseOrder = new PurchaseOrder();
            thepurchaseOrder.setPurchaseDate(new Date());
            thepurchaseOrder.setSupplierId(thepurchaseOrderDTO.getSupplierId());

            List<PurchaseProductDTO> products = thepurchaseOrderDTO.getProducts();

            for (PurchaseProductDTO purchaseProductDTO : products) {
                if (purchaseProductDTO.getProductName() == null || purchaseProductDTO.getProductVolume() <= 0 || purchaseProductDTO.getCostPrice() <= 0 || purchaseProductDTO.getPurchaseQuantity() <= 0) {
                    throw new IllegalArgumentException("Product name, volume, price, and quantity must be provided.");
                }

                thepurchaseOrder.setPurchaseQuantity(thepurchaseOrder.getPurchaseQuantity() + purchaseProductDTO.getPurchaseQuantity());
                thepurchaseOrder.setTotalCostPrice(thepurchaseOrder.getTotalCostPrice() + purchaseProductDTO.getPurchaseQuantity() * purchaseProductDTO.getCostPrice());
                thepurchaseOrder.addProduct(purchaseProductDTO);

                Product product = productRepository.findProductByGodownIdAndProductName(thepurchaseOrderDTO.getGodownId(), purchaseProductDTO.getProductName());
                if (product != null) {
                    product.setTotalQuantity(product.getTotalQuantity() + purchaseProductDTO.getPurchaseQuantity());
                    productRepository.save(product);
                } else {
                    Product product1 = new Product(purchaseProductDTO.getProductName(), purchaseProductDTO.getProductVolume(), purchaseProductDTO.getCostPrice(), purchaseProductDTO.getPurchaseQuantity(), thepurchaseOrderDTO.getGodownId());
                    productRepository.save(product1);
                }
            }

            PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(thepurchaseOrder);
            return "Purchase order created with id: " + savedPurchaseOrder.getPurchaseId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//    public String createPurchaseOrder(PurchaseOrder thepurchaseOrder) {
//        try {
//            thepurchaseOrder.setPurchaseDate(new Date());
//            PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(thepurchaseOrder);
//            return "Purchase order created with id: " + savedPurchaseOrder.getPurchaseId();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }



    public Optional<Supplier> getSupplierByPurchaseOrderId(int purchaseId) {
        Optional<PurchaseOrder> thePurchaseOrder = purchaseOrderRepository.findById(purchaseId);
        if (thePurchaseOrder.isEmpty()) {
            throw new IllegalArgumentException("Purchase order with the provided ID does not exist.");
        }
        return supplierRepository.findById(thePurchaseOrder.get().getSupplierId());
    }


//    public Optional<List<Product>> getProductByPurchaseOrderId(int purchaseId) {
//        Optional<PurchaseOrder> thePurchaseOrder = purchaseOrderRepository.findById(purchaseId);
//        return Optional.ofNullable(thePurchaseOrder
//    }

}
