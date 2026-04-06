package com.example.bank.Model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Transaction {
    private String date;
    private int amount;
    private int balance;



}

