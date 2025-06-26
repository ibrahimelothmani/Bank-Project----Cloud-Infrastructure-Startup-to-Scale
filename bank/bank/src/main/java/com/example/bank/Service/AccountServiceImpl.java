package com.example.bank.Service;
import com.example.bank.Model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final List<Transaction> transactions = new ArrayList<>();
    private int balance = 0;

    @Override
    public void deposit(int amount, String date) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid amount");
        balance += amount;
        transactions.add(new Transaction(date, amount, balance));
    }

    @Override
    public void withdraw(int amount, String date) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid amount");
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds");
        balance -= amount;
        transactions.add(new Transaction(date, -amount, balance));
    }

    @Override
    public List<Transaction> getStatement() {
        List<Transaction> reversed = new ArrayList<>(transactions);
        Collections.reverse(reversed);
        return reversed;
    }

    public void printStatement() {
        System.out.println("Date       || Amount || Balance");
        System.out.println("--------------------------------");

        List<Transaction> reversed = new ArrayList<>(transactions);
        Collections.reverse(reversed);

        for (Transaction t : reversed) {
            System.out.printf("%-10s || %7d || %8d\n", t.getDate(), t.getAmount(), t.getBalance());
        }
    }}

