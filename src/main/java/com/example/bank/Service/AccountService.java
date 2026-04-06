package com.example.bank.Service;

import com.example.bank.Model.Transaction;

import java.util.List;

public interface AccountService {
    void deposit(int amount, String date);
    void withdraw(int amount, String date);
    List<Transaction> getStatement();
}
