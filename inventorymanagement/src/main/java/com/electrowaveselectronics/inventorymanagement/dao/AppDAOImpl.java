package com.electrowaveselectronics.inventorymanagement.dao;

import com.electrowaveselectronics.inventorymanagement.entity.Godown;
import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.entity.PurchaseOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveGodown(Godown godown) {
        entityManager.persist(godown);
    }

    @Override
    @Transactional
    public void updateGodown(Godown godown) {
        entityManager.merge(godown);

    }

    @Override
    public Godown findGodownById(int theId) {
        return entityManager.find(Godown.class, theId);
    }

    @Override
    public List<Godown> listGodowns() {
        TypedQuery<Godown> query = entityManager.createQuery("SELECT e FROM Godown e", Godown.class);
        List<Godown> results = query.getResultList();
        return results;
    }

    @Override
    public Product findProductByProductName(String productName, int godownId) {
        TypedQuery<Product> typedQuery = entityManager.createQuery(
                "SELECT u FROM Product u WHERE u.godownId=:id AND u.productName=:name", Product.class
        );
        typedQuery.setParameter("id", godownId);
        typedQuery.setParameter("name", productName);
        Product product = typedQuery.getSingleResult();
//        System.out.println(product.toString());
        return product;
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        entityManager.merge(product);
    }

    @Override
    @Transactional
    public void savepurchaseorder(PurchaseOrder tempOrder) {
        entityManager.persist(tempOrder);
    }
    @Override
    @Transactional
    public void updatepurchaseorder(PurchaseOrder tempOrder) {
        entityManager.merge(tempOrder);
    }



}
