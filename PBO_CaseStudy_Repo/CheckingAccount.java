// ============================================================
//  INHERITANCE — CheckingAccount also extends BankAccount,
//  but with a DIFFERENT withdrawal rule: the balance may go
//  negative (overdraft) down to -Rp500,000.
//  Checking accounts earn no interest.
// ============================================================
public class CheckingAccount extends BankAccount {

    private static final double OVERDRAFT_LIMIT = 500000;

    public CheckingAccount(String accountNumber, String ownerName, double initialBalance) {
        super(accountNumber, ownerName, initialBalance); // call the parent constructor
    }

    // Implementation of the abstract method:
    // withdrawal is allowed while balance - amount >= -OVERDRAFT_LIMIT.
    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && balance - amount >= -OVERDRAFT_LIMIT) {
            balance -= amount;
            System.out.printf("%s (Checking) withdrew Rp%,.0f -> balance Rp%,.0f%n",
                    ownerName, amount, balance);
            return true;
        }
        System.out.printf("%s (Checking) withdrawal of Rp%,.0f REJECTED (overdraft limit Rp%,.0f)%n",
                ownerName, amount, OVERDRAFT_LIMIT);
        return false;
    }

    @Override
    public double calculateInterest() {
        return 0; // checking accounts earn no interest
    }

    @Override
    public String getAccountType() {
        return "Checking Account";
    }
}
