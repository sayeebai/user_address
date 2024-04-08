package com.sayu.user_address_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class UserAddressCrudApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(UserAddressCrudApplication.class, args);
	}



	// JSR Validation in dto
	// voilastion method to validate dto
	// generic validation method and exception handller
}
