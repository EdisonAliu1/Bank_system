package com.linkplus.banksystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDatabase {
    private static final Map<Integer, Account> accounts = new HashMap<>();

    public static void saveAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public static Account getAccount(int accountId) {
        return accounts.get(accountId);
    }

    public static List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }
    public static void printAllAccounts() {
        System.out.println("All Accounts:");
        for (Account account : accounts.values()) {
            System.out.println(account);
        }
    }

}