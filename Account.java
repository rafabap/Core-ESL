package accounting;

import agents.Agent;
import actions.Action;
import contracts.Contract;

import java.util.ArrayList;
import java.util.HashSet;

class Account {

    private Account(String name, AccountType accountType, Double startingBalance) {
        this.name = name;
        this.accountType = accountType;
        this.balance = startingBalance;
    }

    Account(String name, AccountType accountType) {
        this(name,accountType,0.0);
    }

    private double balance;

    static void doubleEntry(Account debitAccount, Account creditAccount, double amount) {
        debitAccount.debit(amount);
        creditAccount.credit(amount);
    }

//    private Collateral collateralType;
    private AccountType accountType;
    private String name;


    /**
     * A Debit is a positive change for ASSET and EXPENSES accounts, and negative for the rest.
     * @param amount the amount to debit
     */
    private void debit(double amount) {
        if ((accountType==AccountType.ASSET) || (accountType==AccountType.EXPENSES)) {
            balance += amount;
        } else {
            balance -= amount;
        }
    }

    /**
     * A Credit is a negative change for ASSET and EXPENSES accounts, and positive for the rest.
     * @param amount the amount to credit
     */
    private void credit(double amount) {
        if ((accountType==AccountType.ASSET) || (accountType==AccountType.EXPENSES)) {
            balance -= amount;
        } else {
            balance += amount;
        }
    }

    AccountType getAccountType() {
        return accountType;
    }

    double getBalance() {
        return balance;
    }

    String getName() {
        return name;
    }
}
