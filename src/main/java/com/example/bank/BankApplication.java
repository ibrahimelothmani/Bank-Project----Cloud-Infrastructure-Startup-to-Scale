package com.example.bank;

import com.example.bank.Service.AccountServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
        AccountServiceImpl account = new AccountServiceImpl();

        account.deposit(1000, "10/01/2012");
        account.deposit(2000, "13/01/2012");
        account.withdraw(500, "14/01/2012");

        account.printStatement();
    }
	}

