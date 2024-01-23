package com.electrowaveselectronics.inventorymanagement;

import com.electrowaveselectronics.inventorymanagement.entity.Godown;
import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.entity.PurchaseOrder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework. context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Date;

@EnableJpaRepositories(basePackages = "com.electrowaveselectronics.inventorymanagement.repository")
@EntityScan(basePackages = "com.electrowaveselectronics.inventorymanagement.entity")
@ComponentScan(basePackages = "com.electrowaveselectronics.inventorymanagement")
@SpringBootApplication
public class InventorymanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorymanagementApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(AppDAO appDAO){
//		return runner ->{
//			// Godown
//
////			createOrder(appDAO);
//
//		};
//	}
//
//	private void createOrder(AppDAO appDAO) {
//		int godownId=2;
//		PurchaseOrder tempOrder = new PurchaseOrder(
//				new Date(2024, 11, 18), 200, 30
//		);
//
//		Product p1 = new Product("heater",1,500,50);
//		Product exisitingProduct =appDAO.findProductByProductName(p1.getProductName(),godownId);
//		if( exisitingProduct!=null){
//			exisitingProduct.setTotalQuantity(exisitingProduct.getTotalQuantity()+p1.getTotalQuantity());
//			appDAO.updateProduct(exisitingProduct);
//			tempOrder.addProducts(exisitingProduct);
//			appDAO.updatepurchaseorder(tempOrder);
//
//
//		}
//		else {
//			tempOrder.addProducts(p1);
//			appDAO.savepurchaseorder(tempOrder);
//		}
//	}
//


}
