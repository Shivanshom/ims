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


}
