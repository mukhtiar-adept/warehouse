package com.progressoft.warehouse;

import com.progressoft.warehouse.repository.CurrencyCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 *
 * @author  mukhtiar.ahmed
 * version 1.0
 */
@EnableJpaAuditing
@SpringBootApplication
public class WarehouseApplication {

    @Autowired
    CurrencyCodeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}
}
