package com.electrowaveselectronics.inventorymanagement;


import com.electrowaveselectronics.inventorymanagement.service.LoginService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
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

	@Bean
	public CommandLineRunner commandLineRunner(LoginService loginService){
		return runner -> {
			loginService.registerAdmin("shivansh", "123456789","Shivansh","shivansh@gmail.com","8860903817");
		};
	}
}


