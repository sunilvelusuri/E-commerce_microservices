package com.techEnthusiast.inventoryservice;

import com.techEnthusiast.inventoryservice.model.Inventory;
import com.techEnthusiast.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory=new Inventory();
			inventory.setSkuCode("Macbook");
			inventory.setQuantity(0);

			Inventory inventory1=new Inventory();
			inventory1.setSkuCode("Samsung TV");
			inventory1.setQuantity(2);
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}
}