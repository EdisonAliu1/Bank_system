package com.linkplus.banksystem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class BankSystemRunner implements CommandLineRunner {
    private static Bank bank;

    @Override
    public void run(String... args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bank System!");
        System.out.print("Enter Bank Name: ");
        String bankName = scanner.nextLine();

        double flatFee = 0;
        double percentFee = 0;
        boolean validFees = false;
        while (!validFees) {
            try {
                System.out.print("Enter Transaction Flat Fee Amount: ");
                flatFee = scanner.nextDouble();
                System.out.print("Enter Transaction Percent Fee Value: ");
                percentFee = scanner.nextDouble();
                validFees = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid numeric values for fees.");
                scanner.nextLine();
            }
        }

        bank = new Bank(bankName, flatFee, percentFee);

        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = readIntegerInput(scanner);
            switch (choice) {
                case 1 -> createAccount(scanner);
                case 2 -> performTransaction(scanner);
                case 3 -> withdraw(scanner);
                case 4 -> deposit(scanner);
                case 5 -> listTransactions(scanner);
                case 6 -> checkAccountBalance(scanner);
                case 7 -> listBankAccounts();
                case 8 -> checkBankTotals();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\nBank System Menu:");
        System.out.println("1. Create Account");
        System.out.println("2. Perform Transaction");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Deposit Money");
        System.out.println("5. List Transactions");
        System.out.println("6. Check Account Balance");
        System.out.println("7. List Bank Accounts");
        System.out.println("8. Check Bank Totals");
        System.out.println("0. Exit");
    }

    private void createAccount(Scanner scanner) {
        try {
            System.out.print("Enter Account ID: ");
            int accountId = readIntegerInput(scanner);
            scanner.nextLine(); // Consume the newline character
            System.out.print("Enter User Name: ");
            String userName = scanner.nextLine();
            System.out.print("Enter Initial Balance: ");
            double initialBalance = scanner.nextDouble();
            bank.createAccount(accountId, userName, initialBalance);
            System.out.println("Account created successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void performTransaction(Scanner scanner) {
        try {
            System.out.print("Enter From Account ID: ");
            int fromAccountId = readIntegerInput(scanner);
            System.out.print("Enter To Account ID: ");
            int toAccountId = readIntegerInput(scanner);
            System.out.print("Enter Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character
            System.out.print("Enter Reason: ");
            String reason = scanner.nextLine();
            bank.performTransaction(fromAccountId, toAccountId, amount, reason);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void withdraw(Scanner scanner) {
        try {
            System.out.print("Enter Account ID: ");
            int accountId = readIntegerInput(scanner);
            System.out.print("Enter Withdraw Amount: ");
            double amount = scanner.nextDouble();
            bank.withdraw(accountId, amount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void deposit(Scanner scanner) {
        try {
            System.out.print("Enter Account ID: ");
            int accountId = readIntegerInput(scanner);
            System.out.print("Enter Deposit Amount: ");
            double amount = scanner.nextDouble();
            bank.deposit(accountId, amount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void listTransactions(Scanner scanner) {
        try {
            System.out.print("Enter Account ID: ");
            int accountId = readIntegerInput(scanner);
            bank.listTransactions(accountId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void checkAccountBalance(Scanner scanner) {
        try {
            System.out.print("Enter Account ID: ");
            int accountId = readIntegerInput(scanner);
            bank.checkAccountBalance(accountId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void listBankAccounts() {
        bank.listBankAccounts();
    }

    private void checkBankTotals() {
        System.out.println("Total Transaction Fee Amount: $" + bank.getTotalTransactionFeeAmount());
        System.out.println("Total Transfer Amount: $" + bank.getTotalTransferAmount());
    }

    private int readIntegerInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
