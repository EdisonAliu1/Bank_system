package com.linkplus.banksystem;

import java.util.ArrayList;
import java.util.List;


public class Bank {
    private String name;
    private List<Account> accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentFeeValue;

    public Bank(String name, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        this.name = name;
        this.accounts = new ArrayList<>();
        this.totalTransactionFeeAmount = 0;
        this.totalTransferAmount = 0;
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
        this.transactionPercentFeeValue = transactionPercentFeeValue;
    }

    public void createAccount(int accountId, String userName, double initialBalance) {
        Account account = new Account(accountId, userName, initialBalance);
        accounts.add(account);
    }

    public void performTransaction(int fromAccountId, int toAccountId, double amount, String reason) {
        Account fromAccount = findAccountById(fromAccountId);
        Account toAccount = findAccountById(toAccountId);

        if (fromAccount != null && toAccount != null) {
            double transactionFee = calculateTransactionFee(amount);
            if (fromAccount.withdraw(amount + transactionFee)) {
                toAccount.deposit(amount);
                totalTransactionFeeAmount += transactionFee;
                totalTransferAmount += amount;
                System.out.println("Transaction successful: $" + amount + " transferred from account " + fromAccountId +
                        " to account " + toAccountId + ". Reason: " + reason);
            } else {
                System.out.println("Transaction failed: Insufficient funds.");
            }
        } else {
            System.out.println("Transaction failed: One or both accounts not found.");
        }
    }

    private double calculateTransactionFee(double amount) {
        return transactionFlatFeeAmount + (amount * transactionPercentFeeValue / 100);
    }

    public void withdraw(int accountId, double amount) {
        Account account = findAccountById(accountId);
        if (account != null) {
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful: $" + amount + " withdrawn from account " + accountId);
            } else {
                System.out.println("Withdrawal failed: Insufficient funds.");
            }
        } else {
            System.out.println("Withdrawal failed: Account not found.");
        }
    }

    public void deposit(int accountId, double amount) {
        Account account = findAccountById(accountId);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful: $" + amount + " deposited to account " + accountId);
        } else {
            System.out.println("Deposit failed: Account not found.");
        }
    }

    public void listTransactions(int accountId) {
        Account account = findAccountById(accountId);
        if (account != null) {
            account.listTransactions();
        } else {
            System.out.println("Account not found.");
        }
    }

    public void checkAccountBalance(int accountId) {
        Account account = findAccountById(accountId);
        if (account != null) {
            System.out.println("Account " + accountId + " balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    public void listBankAccounts() {
        System.out.println("Bank Accounts:");
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    private Account findAccountById(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }
}