package com.linkplus.banksystem;


import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountId;
    private String userName;
    private double balance;
    private List<Transaction> transactions;

    public Account(int accountId, String userName, double initialBalance) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public int getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction(-amount, accountId, accountId, "Withdrawal"));
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction(amount, accountId, accountId, "Deposit"));
    }

    public void listTransactions() {
        System.out.println("Transactions for Account " + accountId + ":");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", userName='" + userName + '\'' +
                ", balance=$" + balance +
                '}';
    }
}