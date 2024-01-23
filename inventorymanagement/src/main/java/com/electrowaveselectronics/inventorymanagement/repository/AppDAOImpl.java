//package com.electrowaveselectronics.inventorymanagement.repository;
//
//import com.electrowaveselectronics.inventorymanagement.entity.Godown;
//import com.electrowaveselectronics.inventorymanagement.entity.Product;
//import com.electrowaveselectronics.inventorymanagement.entity.PurchaseOrder;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.TypedQuery;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Repository
//public class AppDAOImpl implements AppDAO {
//
//    private EntityManager entityManager;
//
//    @Autowired
//    public AppDAOImpl(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    @Transactional
//    public void savepurchaseorder(PurchaseOrder tempOrder) {
//        entityManager.persist(tempOrder);
//    }
//    @Override
//    @Transactional
//    public void updatepurchaseorder(PurchaseOrder tempOrder) {
//        entityManager.merge(tempOrder);
//    }
//
//
//
//}
