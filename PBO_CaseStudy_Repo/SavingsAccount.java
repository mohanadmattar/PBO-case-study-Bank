// ============================================================
//  INHERITANCE — SavingsAccount extends BankAccount.
//  It inherits all attributes and methods of BankAccount and
//  adds its own rule: a minimum balance of Rp50,000 must remain
//  in the account after every withdrawal. Interest: 0.5%/month.
// ============================================================
public class SavingsAccount extends BankAccount {

    private static final double MIN_BALANCE = 50000;
    private static final double INTEREST_RATE = 0.005; // 0.5% per month

    public SavingsAccount(String accountNumber, String ownerName, double initialBalance) {
        super(accountNumber, ownerName, initialBalance); // call the parent constructor
    }

    // Implementation of the abstract method:
    // withdrawal is allowed only if the remaining balance >= MIN_BALANCE.
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && balance - amount >= MIN_BALANCE) {
            balance -= amount;
            System.out.printf("%s (Savings) withdrew Rp%,.0f -> balance Rp%,.0f%n",
                    ownerName, amount, balance);
            return true;
        }
        System.out.printf("%s (Savings) withdrawal of Rp%,.0f REJECTED (minimum balance Rp%,.0f must remain)%n",
                ownerName, amount, MIN_BALANCE);
        return false;
    }

    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }
}
