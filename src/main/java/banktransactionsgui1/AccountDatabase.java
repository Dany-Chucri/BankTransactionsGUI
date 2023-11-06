package banktransactionsgui1;

import javafx.scene.control.TextArea;

import java.text.DecimalFormat;

/**
 * Holds a list of Account objects to form an account database.
 * Contains an accounts array as well as a number of accounts tracker.
 * Size of the accounts array can increase if needed but not decrease.
 @author Dany Chucri, Madhur Nutulapati
 */

public class AccountDatabase {
    private Account [] accounts; // list of various types of accounts

    private int numAcct; // number of accounts in the array

    private static final int baseSize = 4;

    private static final int NOT_FOUND = -1;

    private static final int NUM_MONTHS = 12;

    /**
     Creates an instance of Account Database and initialize numAcct to 0.
     */
    public AccountDatabase() {
        accounts = new Account[baseSize];
        numAcct = 0;
    }

    /**
     * Searches for a specified account object in the list.
     * Uses the criterion specified in account's compareTo() method.
     * @param account The account to be searched for.
     * @return An integer representing the position of the Account in the AccountDatabase.
     */
    private int find(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].compareTo(account) == 0) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Searches for a specified account object in the list.
     * Uses the criterion specified in account's compareTo() method.
     * Specifically for opening an account
     * @param account The account to be searched for.
     * @return true if its found; false for not found.
     */
    private boolean openFind(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].toString().substring(0, 1).compareTo(account.toString().substring(0, 1)) == 0) {
                if (accounts[i].getHolder().compareTo(account.getHolder()) == 0) return true;
            }
        }
        return false;
    }

    /**
     * Increases the capacity of the Accounts array by adding 4 account slots.
     */
    private void grow(){
        Account[] moreAccounts = new Account[accounts.length + 4];
        for (int i = 0; i < numAcct; i++) {
            moreAccounts[i] = accounts[i];
        }
        accounts = moreAccounts;
    } //increase the capacity by 4

    /**
     * Checks if the specified Account is already in the AccountDatabase.
     * @param account Account to be looked for.
     * @return True if the Account is in the AccountDatabase, otherwise false.
     */
    public boolean contains(Account account){
        for (int i = 0 ; i < numAcct; i++) {
            if (accounts[i].compareTo(account) == 0) {
                return true;
            }
        }
        return false;
    } //overload if necessary

    /**
     * Opens a given Account Object to the Accounts array of AccountDatabase.
     * @param account The account to be opened.
     * @return True if the account was opened, otherwise false.
     */
    public boolean open(Account account) {
        if (numAcct == 0) {
            accounts[0] = account;
            numAcct++;
            return true;
        }
        if (openFind(account)) {
            return false;
        }
        if (numAcct == accounts.length)
            this.grow();
        accounts[numAcct] = account;
        numAcct++;
        return true;
    } //add a new account

    /**
     * Closes/removes a specified Account Object from the accounts list.
     * @param account The account to be closed.
     * @return True if the account was closed, otherwise false.
     */
    public boolean close(Account account) {
        int foundAccount = find(account);
        if (foundAccount != NOT_FOUND) {
            for(int j=foundAccount; j<numAcct-1;j++) {
                accounts[j] = accounts[j+1];
            }
            numAcct--;
            return true;
        }
        else{
            return false;
        }
    } //remove the given account

    /**
     * Withdraws a certain amount from a specified Account Object from the accounts list.
     * @param account The account to withdraw from.
     * @return True if the withdrawal operation happened successfully, otherwise false.
     */
    public boolean withdraw(Account account) {
        int foundAccount = find(account);
        if (foundAccount != NOT_FOUND) {
            double newBalance = accounts[foundAccount].getBalance() - account.getBalance();
            if (newBalance < 0) return false;
            accounts[foundAccount].setBalance(newBalance);
            if (accounts[foundAccount] instanceof MoneyMarket acc) {
                    double mmBalance = acc.getBalance();
                    if (mmBalance < 0) {
                        acc.setBalance(acc.getBalance() + account.getBalance());
                        return false;
                    }
                    else {
                        acc.setBalance(mmBalance);
                    }
                if (newBalance < 2000 && (acc.getLoyalty())) {
                    acc.setLoyalty(false);
                }
                acc.incWithdrawal();
            }
            return true;
        }
        return false;
    }//false if insufficient fund

    /**
     * responsible for processing a deposit into a specified account from the accounts list.
     * @param account The account to deposit into.
     */
    public void deposit(Account account) {
        int foundAccount = find(account);
        if (foundAccount != NOT_FOUND) {
            double newBalance = accounts[foundAccount].getBalance() + account.getBalance();
            accounts[foundAccount].setBalance(newBalance);
            if (accounts[foundAccount] instanceof MoneyMarket acc) {
                if (newBalance >= 2000 && !(acc.getLoyalty())) {
                    acc.setLoyalty(true);
                }
            }
        }
    }

    /**
     * Used to sort the AccountDatabase based off of Account Types.
     * Utilizes a selection sort algorithm.
     */
    private void selectionSortAccounts(){
        for (int i = 0; i < numAcct - 1; i++){
            int min = i;
            for (int j = i + 1; j < numAcct; j++){
                if (accounts[j].compareTo(accounts[min]) == 0) {
                    if (accounts[j].getHolder().compareTo(accounts[min].getHolder()) < 0) {
                        min = j;
                    }
                }
                else if (accounts[j].compareTo(accounts[min]) < 0){
                    min = j;
                }
            }
            Account minDateEvent = accounts[min];
            accounts[min] = accounts[i];
            accounts[i] = minDateEvent;
        }
    }

    /**
     * Prints out the accounts of the database, sorted by the account types.
     * Calls selectionSortAccounts() method
     */
    public void printSorted(TextArea textArea) {
        selectionSortAccounts();
        if (numAcct == 0){
            textArea.appendText("Account Database is empty!\n");
            return;
        }
        textArea.appendText("\n*Accounts sorted by account type and profile.\n");
        for(int i = 0; i < numAcct; i++){
            textArea.appendText(accounts[i].toString() + "\n");
        }
        textArea.appendText("*end of list.\n\n");
    } // sort by account type and profile, then print

    /**
     * responsible for calculating the monthly fee using instance of operator to determine account type
     * calling monthly fee of respective account to calculate monthly fee.
     * @param account The account.
     * @return monthly fee for given account
     */
    private double calcMonthlyFee(Account account) {
        if (account instanceof CollegeChecking acc) {
            return acc.monthlyFee();
        }
        else if (account instanceof Checking acc) {
            return acc.monthlyFee();
        }
        else if (account instanceof MoneyMarket acc) {
            return acc.monthlyFee();
        }
        else if (account instanceof Savings acc) {
            return acc.monthlyFee();
        }
        return 0;
    }

    /**
     * responsible for calculating the monthly interest using instance of operator to determine account type
     * calling monthlyInterest of respective account to calculate monthly interest.
     * @param account The account.
     * @return monthly interest for given account
     */
    private double calcMonthlyInterest(Account account) {
        if (account instanceof CollegeChecking acc) {
            return acc.monthlyInterest();
        }
        else if (account instanceof Checking acc) {
            return acc.monthlyInterest();
        }
        else if (account instanceof MoneyMarket acc) {
            return acc.monthlyInterest();
        }
        else if (account instanceof Savings acc) {
            return acc.monthlyInterest();
        }
        return 0;

    }

    /**
     * Responsible for displaying all account.
     * Will also display the calculated fees and monthly interests based on current balances
     */
    public void printFeesAndInterests(TextArea textArea) {
        selectionSortAccounts();
        if (numAcct == 0){
            textArea.appendText("Account Database is empty!\n");
            return;
        }
        textArea.appendText("\n*list of accounts with fee and monthly interest\n");
        for (int i = 0; i < numAcct; i++)
        {
            double fee = calcMonthlyFee(accounts[i]);
            double interest = calcMonthlyInterest(accounts[i]);
            DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            String feeString = formatter.format(fee);
            String interestString = formatter.format(interest);
            textArea.appendText(accounts[i].toString() + "::fee " + feeString + "::monthly interest " + interestString + "\n");
        }
        textArea.appendText("*end of list.\n\n");
    } //calculate interests/fees, then print

    /**
     * calculates and prints the updated balances of all accounts in the database after applying monthly fees and interests.
     */
    public void printUpdatedBalances(TextArea textArea) {
        selectionSortAccounts();
        if (numAcct == 0){
            textArea.appendText("Account Database is empty!\n");
            return;
        }
        textArea.appendText("\n*list of accounts with fees and interests applied.\n" );
        for (int i = 0; i < numAcct; i++)
        {
            double fee = calcMonthlyFee(accounts[i]);
            double interest = calcMonthlyInterest(accounts[i]);
            accounts[i].setBalance(accounts[i].getBalance() - fee + interest);
            if (accounts[i] instanceof MoneyMarket acc) {
                acc.setWithdrawal(0);
            }
            textArea.appendText(accounts[i].toString() + "\n");
        }
        textArea.appendText("*end of list.\n\n");
    } //apply the interests/fees, then print
}