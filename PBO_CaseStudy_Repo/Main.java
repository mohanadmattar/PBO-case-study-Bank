// ============================================================
//  POLYMORPHISM DEMO
//  One array of the abstract type BankAccount holds objects of
//  DIFFERENT subclasses. The same method call, withdraw(),
//  behaves differently depending on the real object type:
//    - SavingsAccount  : keeps a minimum balance of Rp50,000
//    - CheckingAccount : allows overdraft down to -Rp500,000
// ============================================================
public class Main {

    public static void main(String[] args) {
        BankAccount[] accounts = {
            new SavingsAccount("SA-001", "Mohanad", 1000000),
            new CheckingAccount("CA-001", "Mohanad", 300000)
        };

        for (BankAccount account : accounts) {
            System.out.println("=== " + account.getAccountType()
                    + " | " + account.getAccountNumber() + " ===");

            account.deposit(200000);        // shared method from the abstract class
            account.withdraw(1100000);      // DIFFERENT rule per subclass -> polymorphism
            account.applyMonthlyInterest(); // calls each subclass's calculateInterest()

            System.out.printf("Final balance: Rp%,.0f%n%n", account.getBalance());
        }
    }
}
