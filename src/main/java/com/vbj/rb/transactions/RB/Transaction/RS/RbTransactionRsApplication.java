package com.vbj.rb.transactions.RB.Transaction.RS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value="com.vbj.rb")
public class RbTransactionRsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbTransactionRsApplication.class, args);
	}
}
