package com.setu.billsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class BillsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillsystemApplication.class, args);
	}

}
