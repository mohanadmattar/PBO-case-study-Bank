// ============================================================
//  ABSTRACT CLASS — BankAccount
//  An abstract class cannot be instantiated directly.
//  It defines the shared attributes and behavior of every bank
//  account, and forces each subclass to implement its own
//  withdrawal rules and interest policy.
// ============================================================
public abstract class BankAccount {

    // Shared attributes (protected: visible to subclasses)
    protected String accountNumber;
    protected String ownerName;
    protected double balance;

    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    // Concrete method: the deposit rule is the same for all account types,
    // so it is implemented once here in the abstract class.
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("%s deposited Rp%,.0f -> balance Rp%,.0f%n",
                    ownerName, amount, balance);
        }
    }

    // Abstract methods: no body here.
    // Every subclass MUST provide its own implementation.
    public abstract boolean withdraw(double amount);
    public abstract double calculateInterest();
    public abstract String getAccountType();

    // Concrete method that calls an abstract method.
    // At runtime, Java runs the SUBCLASS version of calculateInterest()
    // and getAccountType() — this is polymorphism.
    public void applyMonthlyInterest() {
        double interest = calculateInterest();
        balance += interest;
        System.out.printf("%s (%s) interest: Rp%,.0f -> balance Rp%,.0f%n",
                getAccountType(), ownerName, interest, balance);
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }
}
