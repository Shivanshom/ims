package com.electrowaveselectronics.inventorymanagement;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.electrowaveselectronics.inventorymanagement.repository")
@EntityScan(basePackages = "com.electrowaveselectronics.inventorymanagement.entity")
@ComponentScan(basePackages = "com.electrowaveselectronics.inventorymanagement")
@SpringBootApplication
public class InventorymanagementApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(InventorymanagementApplication.class, args);}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(InventorymanagementApplication.class);
	}
}


