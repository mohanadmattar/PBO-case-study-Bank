# Case Study: Abstract Class — Bank Account System

**Name:** Mohanad R. M. Abumattar
**NIM:** F1D02411002
**Class:** 5A — Object Oriented Programming (PBO)

A case study applying **Abstract Class**, **Inheritance**, and **Polymorphism** to a simple bank account system in Java.

## File Structure

| File | Contents |
|---|---|
| `BankAccount.java` | Abstract class — the general template of every bank account |
| `SavingsAccount.java` | Subclass: savings account (minimum balance + interest) |
| `CheckingAccount.java` | Subclass: checking account (overdraft allowed, no interest) |
| `Main.java` | Main program — polymorphism demonstration |
| `ClassDiagram.png` | UML class diagram of this program |

## Concepts Used

### 1. Abstract Class
`BankAccount` is declared with the `abstract` keyword, so it **cannot be instantiated directly**. It acts as a template for every account type:

- Shared attributes: `accountNumber`, `ownerName`, `balance`
- Concrete methods (already implemented, shared by all subclasses): `deposit()`, `applyMonthlyInterest()`, and the getters
- Abstract methods (no body — every subclass **must** implement them): `withdraw()`, `calculateInterest()`, `getAccountType()`

### 2. Inheritance
`SavingsAccount` and `CheckingAccount` inherit from `BankAccount` using `extends`. Both reuse the parent's attributes and `deposit()` method, call the parent constructor with `super(...)`, and implement the abstract methods with different rules:

| Rule | SavingsAccount | CheckingAccount |
|---|---|---|
| Withdrawal | Minimum balance of Rp50,000 must remain | Balance may go negative (overdraft) down to -Rp500,000 |
| Monthly interest | 0.5% of the balance | 0 (no interest) |

### 3. Polymorphism
In `Main`, one array of the parent type holds objects of different subclasses:

```java
BankAccount[] accounts = {
    new SavingsAccount("SA-001", "Mohanad", 1000000),
    new CheckingAccount("CA-001", "Mohanad", 300000)
};
```

The same method call produces different behavior depending on the actual object type. The withdrawal `withdraw(1100000)`:
- **Succeeds** on `SavingsAccount` — balance after deposit is Rp1,200,000, leaving Rp100,000, still above the minimum.
- **Is rejected** on `CheckingAccount` — the result would be -Rp600,000, passing the overdraft limit of -Rp500,000.

`applyMonthlyInterest()` is polymorphic too: it calls each subclass's own `calculateInterest()`, so only the SavingsAccount balance grows.

## How to Run

```bash
javac BankAccount.java SavingsAccount.java CheckingAccount.java Main.java
java Main
```

## Sample Output

```
=== Savings Account | SA-001 ===
Mohanad deposited Rp200,000 -> balance Rp1,200,000
Mohanad (Savings) withdrew Rp1,100,000 -> balance Rp100,000
Savings Account (Mohanad) interest: Rp500 -> balance Rp100,500
Final balance: Rp100,500

=== Checking Account | CA-001 ===
Mohanad deposited Rp200,000 -> balance Rp500,000
Mohanad (Checking) withdrawal of Rp1,100,000 REJECTED (overdraft limit Rp500,000)
Checking Account (Mohanad) interest: Rp0 -> balance Rp500,000
Final balance: Rp500,000
```

Notice: the same `deposit(200000)` and `withdraw(1100000)` calls produce different results — that is polymorphism.
