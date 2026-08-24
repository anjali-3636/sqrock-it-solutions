import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {

    private String accountNumber;
    private String holderName;
    private String accountType;
    private double balance;

    private ArrayList<String> transactions = new ArrayList<>();

    public BankAccount(String accountNumber, String holderName,
                       String accountType, double balance) {

        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.accountType = accountType;
        this.balance = balance;

        transactions.add("Account created with balance: Rs." + balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void displayAccount() {

        System.out.println("\n---------- ACCOUNT DETAILS ----------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Holder Name    : " + holderName);
        System.out.println("Account Type   : " + accountType);
        System.out.println("Balance        : Rs." + balance);
        System.out.println("-------------------------------------");
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        balance += amount;
        transactions.add("Deposited: Rs." + amount);

        System.out.println("Amount deposited successfully.");
        System.out.println("Current Balance: Rs." + balance);
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return;
        }

        balance -= amount;
        transactions.add("Withdrawn: Rs." + amount);

        System.out.println("Amount withdrawn successfully.");
        System.out.println("Current Balance: Rs." + balance);
    }

    public boolean transferTo(BankAccount receiver, double amount) {

        if (amount <= 0) {
            System.out.println("Invalid transfer amount!");
            return false;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return false;
        }

        balance -= amount;
        receiver.balance += amount;

        transactions.add(
                "Transferred Rs." + amount +
                " to Account " + receiver.accountNumber
        );

        receiver.transactions.add(
                "Received Rs." + amount +
                " from Account " + accountNumber
        );

        System.out.println("Money transferred successfully.");
        System.out.println("Current Balance: Rs." + balance);

        return true;
    }

    public void showTransactions() {

        System.out.println("\n---------- TRANSACTION HISTORY ----------");

        for (String transaction : transactions) {
            System.out.println(transaction);
        }

        System.out.println("------------------------------------------");
    }
}

public class OnlineBanking {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("        ONLINE BANKING SYSTEM");
        System.out.println("======================================");

        while (true) {

            System.out.println("\n1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Money");
            System.out.println("6. Transaction History");
            System.out.println("7. Exit");

            System.out.print("\nEnter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    viewAccount();
                    break;

                case 3:
                    depositMoney();
                    break;

                case 4:
                    withdrawMoney();
                    break;

                case 5:
                    transferMoney();
                    break;

                case 6:
                    transactionHistory();
                    break;

                case 7:
                    System.out.println(
                            "\nThank you for using Online Banking System!"
                    );
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void createAccount() {

        System.out.println("\n---------- CREATE ACCOUNT ----------");

        System.out.print("Enter account number: ");
        String accountNumber = sc.nextLine();

        if (findAccount(accountNumber) != null) {
            System.out.println("Account already exists!");
            return;
        }

        System.out.print("Enter account holder name: ");
        String holderName = sc.nextLine();

        System.out.print("Enter account type (Savings/Current): ");
        String accountType = sc.nextLine();

        System.out.print("Enter initial balance: ");

        double balance;

        try {
            balance = Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid balance!");
            return;
        }

        if (balance < 0) {
            System.out.println("Balance cannot be negative!");
            return;
        }

        BankAccount account = new BankAccount(
                accountNumber,
                holderName,
                accountType,
                balance
        );

        accounts.add(account);

        System.out.println("Account created successfully!");
    }

    static BankAccount findAccount(String accountNumber) {

        for (BankAccount account : accounts) {

            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

    static BankAccount getAccountFromUser() {

        System.out.print("Enter account number: ");

        String accountNumber = sc.nextLine();

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found!");
        }

        return account;
    }

    static void viewAccount() {

        BankAccount account = getAccountFromUser();

        if (account != null) {
            account.displayAccount();
        }
    }

    static void depositMoney() {

        BankAccount account = getAccountFromUser();

        if (account == null) {
            return;
        }

        System.out.print("Enter deposit amount: ");

        try {

            double amount = Double.parseDouble(sc.nextLine());

            account.deposit(amount);

        } catch (Exception e) {

            System.out.println("Invalid amount!");
        }
    }

    static void withdrawMoney() {

        BankAccount account = getAccountFromUser();

        if (account == null) {
            return;
        }

        System.out.print("Enter withdrawal amount: ");

        try {

            double amount = Double.parseDouble(sc.nextLine());

            account.withdraw(amount);

        } catch (Exception e) {

            System.out.println("Invalid amount!");
        }
    }

    static void transferMoney() {

        System.out.println("\n---------- MONEY TRANSFER ----------");

        System.out.print("Enter sender account number: ");

        String senderNumber = sc.nextLine();

        BankAccount sender = findAccount(senderNumber);

        if (sender == null) {
            System.out.println("Sender account not found!");
            return;
        }

        System.out.print("Enter receiver account number: ");

        String receiverNumber = sc.nextLine();

        BankAccount receiver = findAccount(receiverNumber);

        if (receiver == null) {
            System.out.println("Receiver account not found!");
            return;
        }

        if (sender == receiver) {
            System.out.println(
                    "Sender and receiver cannot be the same!"
            );
            return;
        }

        System.out.print("Enter transfer amount: ");

        try {

            double amount = Double.parseDouble(sc.nextLine());

            sender.transferTo(receiver, amount);

        } catch (Exception e) {

            System.out.println("Invalid amount!");
        }
    }

    static void transactionHistory() {

        BankAccount account = getAccountFromUser();

        if (account != null) {
            account.showTransactions();
        }
    }
}