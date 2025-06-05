package com.userrisktransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringUserRiskTransactionsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringUserRiskTransactionsApplication.class, args);
	}

}
