package com.linkplus.banksystem;

class Transaction {
    private double amount;
    private int fromAccountId;
    private int toAccountId;
    private String reason;

    public Transaction(double amount, int fromAccountId, int toAccountId, String reason) {
        this.amount = amount;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", fromAccountId=" + fromAccountId +
                ", toAccountId=" + toAccountId +
                ", reason='" + reason + '\'' +
                '}';
    }
}