package com.linkplus.banksystem;
import java.util.Scanner;

public class Main {
    private static Bank bank;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bank System!");
        System.out.print("Enter Bank Name: ");
        String bankName = scanner.nextLine();

        System.out.print("Enter Transaction Flat Fee Amount: ");
        double flatFee = scanner.nextDouble();

        System.out.print("Enter Transaction Percent Fee Value: ");
        double percentFee = scanner.nextDouble();

        bank = new Bank(bankName, flatFee, percentFee);

        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    performTransaction(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    deposit(scanner);
                    break;
                case 5:
                    listTransactions(scanner);
                    break;
                case 6:
                    checkAccountBalance(scanner);
                    break;
                case 7:
                    listBankAccounts();
                    break;
                case 8:
                    checkBankTotals();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {
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

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();
        bank.createAccount(accountId, userName, initialBalance);
        System.out.println("Account created successfully!");
    }

    private static void performTransaction(Scanner scanner) throws Exception {
        System.out.print("Enter From Account ID: ");
        int fromAccountId = scanner.nextInt();
        System.out.print("Enter To Account ID: ");
        int toAccountId = scanner.nextInt();
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Reason: ");
        String reason = scanner.nextLine();
        bank.performTransaction(fromAccountId, toAccountId, amount, reason);
    }

    private static void withdraw(Scanner scanner) throws Exception {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter Withdraw Amount: ");
        double amount = scanner.nextDouble();
        bank.withdraw(accountId, amount);
    }

    private static void deposit(Scanner scanner) throws Exception {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter Deposit Amount: ");
        double amount = scanner.nextDouble();
        bank.deposit(accountId, amount);
    }

    private static void listTransactions(Scanner scanner) throws Exception {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        bank.listTransactions(accountId);
    }

    private static void checkAccountBalance(Scanner scanner) throws Exception {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        bank.checkAccountBalance(accountId);
    }

    private static void listBankAccounts() {
        bank.listBankAccounts();
    }

    private static void checkBankTotals() {
        System.out.println("Total Transaction Fee Amount: $" + bank.getTotalTransactionFeeAmount());
        System.out.println("Total Transfer Amount: $" + bank.getTotalTransferAmount());
    }
}

