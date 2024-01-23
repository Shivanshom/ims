package com.electrowaveselectronics.inventorymanagement;

import com.electrowaveselectronics.inventorymanagement.repository.AppDAO;
import com.electrowaveselectronics.inventorymanagement.entity.Godown;
import com.electrowaveselectronics.inventorymanagement.entity.Product;
import com.electrowaveselectronics.inventorymanagement.entity.PurchaseOrder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework. context.annotation.Bean;

import java.util.List;
import java.util.Date;

@SpringBootApplication
public class InventorymanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorymanagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner ->{
			// Godown

		  	createGodown(appDAO);
			addProductByGodownId(appDAO);
			listGodowns(appDAO);
			findProductsByGodownId(appDAO);

//			createOrder(appDAO); //DANGER ⚠️

		};
	}

	private void createOrder(AppDAO appDAO) {
		int godownId=2;
		PurchaseOrder tempOrder = new PurchaseOrder(
				new Date(2024, 11, 18), 200, 30
		);

		Product p1 = new Product("heater",1,500,50);
		Product exisitingProduct =appDAO.findProductByProductName(p1.getProductName(),godownId);
		if( exisitingProduct!=null){
			exisitingProduct.setTotalQuantity(exisitingProduct.getTotalQuantity()+p1.getTotalQuantity());
			appDAO.updateProduct(exisitingProduct);
			tempOrder.addProducts(exisitingProduct);
			appDAO.updatepurchaseorder(tempOrder);


		}
		else {
			tempOrder.addProducts(p1);
			appDAO.savepurchaseorder(tempOrder);
		}


	}

	private void findProductsByGodownId(AppDAO appDAO) {
		int id = 3;
		Godown tempGodown =appDAO.findGodownById(id);
		for (int i=0; i<tempGodown.getProductList().size(); i++){
			System.out.println(tempGodown.getProductList().get(i).toString());
		}
	}

	private void listGodowns(AppDAO appDAO) {
		List<Godown> godowns=appDAO.listGodowns();
		for (int i=0; i<godowns.size(); i++){
			System.out.println(godowns.get(i).toString());
		}
	}

	private void addProductByGodownId(AppDAO appDAO) {
		int id = 1;
		Godown tempGodown = appDAO.findGodownById(id);

		Product newProduct1 = new Product(
				"solar_inverter", 4, 400, 20);

		Product newProduct2 = new Product(
				"ev_charger", 10, 500, 50);
		tempGodown.addProducts(newProduct1);
		tempGodown.addProducts(newProduct2);
		appDAO.updateGodown(tempGodown);
		System.out.println(tempGodown);

//		int id = 1;
//		Godown tempGodown = appDAO.findGodownById(id);
//
//		Product newProduct = new Product(
//				"ev_charger", 2, 200, 2);
//		tempGodown.addProducts(newProduct);
//		appDAO.updateGodown(tempGodown);
	}

	private void createGodown(AppDAO appDAO) {
		Godown newGodown1 = new Godown("Nalagarh", 50);
        Godown newGodown2 = new Godown("Baddi", 100);
        Godown newGodown3 = new Godown("Panchkula", 10);
		appDAO.saveGodown(newGodown1);
		appDAO.saveGodown(newGodown2);
		appDAO.saveGodown(newGodown3);

	}

}
