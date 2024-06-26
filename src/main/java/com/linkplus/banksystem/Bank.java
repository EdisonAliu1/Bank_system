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

    public void createAccount(int accountId, String userName, double initialBalance) throws Exception {
        if (accountId < 0) {
            throw new Exception("Account ID cannot be negative: " + accountId);
        }

        if (InMemoryDatabase.getAccount(accountId) != null) {
            throw new Exception("Account ID already exists: " + accountId);
        }

        Account account = new Account(accountId, userName, initialBalance);
        InMemoryDatabase.saveAccount(account);
    }

    public void performTransaction(int fromAccountId, int toAccountId, double amount, String reason) throws Exception {
        Account fromAccount = InMemoryDatabase.getAccount(fromAccountId);
        Account toAccount = InMemoryDatabase.getAccount(toAccountId);

        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative or 0! ");
        }
        if (fromAccount == null || toAccount == null) {
            throw new Exception("One or both accounts not found.");
        }

        double transactionFee = calculateTransactionFee(amount);
        if (!fromAccount.withdraw(amount + transactionFee)) {
            throw new Exception("Insufficient funds for transaction.");
        }

        toAccount.deposit(amount);
        totalTransactionFeeAmount += transactionFee;
        totalTransferAmount += amount;
        System.out.println("Transaction successful: $" + amount + " transferred from account " + fromAccountId +
                " to account " + toAccountId + ". Reason: " + reason);
    }

    private double calculateTransactionFee(double amount) {
        return transactionFlatFeeAmount + (amount * transactionPercentFeeValue / 100);
    }

    public void withdraw(int accountId, double amount) throws Exception {
        Account account = InMemoryDatabase.getAccount(accountId);
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount cannot be negative or 0 ");
        }
        if (account == null) {
            throw new Exception("Account not found.");
        }

        if (!account.withdraw(amount)) {
            throw new Exception("Insufficient funds for withdrawal.");
        }

        System.out.println("Withdrawal successful: $" + amount + " withdrawn from account " + accountId);
    }

    public void deposit(int accountId, double amount) throws Exception {
        Account account = InMemoryDatabase.getAccount(accountId);
        if (account == null) {
            throw new Exception("Account not found.");
        }

        account.deposit(amount);
        System.out.println("Deposit successful: $" + amount + " deposited to account " + accountId);
    }

    public void listTransactions(int accountId) throws Exception {
        Account account = InMemoryDatabase.getAccount(accountId);
        if (account == null) {
            throw new Exception("Account not found.");
        }

        account.listTransactions();
    }

    public void checkAccountBalance(int accountId) throws Exception {
        Account account = InMemoryDatabase.getAccount(accountId);
        if (account == null) {
            throw new Exception("Account not found.");
        }

        System.out.println("Account " + accountId + " balance: $" + account.getBalance());
    }

    public void listBankAccounts() {
        System.out.println("Bank Accounts:");
        for (Account account : InMemoryDatabase.getAllAccounts()) {
            System.out.println(account);
        }
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }
}
