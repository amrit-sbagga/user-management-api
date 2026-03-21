package com.amrit.user_management_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.amrit.user_management_api.entity")
public class UserManagementApiApplication {

	public static void main(String[] args) {

        SpringApplication.run(UserManagementApiApplication.class, args);

        // System.out.println(System.getenv("DB_PASSWORD"));
	}

}
