package com.example.banktransactionsgui;

/**
 *Implements Comparable interface and represents the general type of account.
 * Attributes for each account holder's profile and balance.
 * Abstract methods for calculating monthly fees & Interests.
 @author Dany Chucri, Madhur Nutulapati
 */
public abstract class Account implements Comparable<Account> {
    protected Profile holder; // Profile of the bank account owner
    protected double balance; // Current balance in the account

    public abstract double monthlyInterest();

    public abstract double monthlyFee();

    /**
     Creates an instance of Account.
     @param holder The profile class object holder.
     @param balance the bank balance given by the user.
     */
    public Account(Profile holder, double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    /**
     Basic getter for an Account's Balance.
     @return The double balance value.
     */
    public double getBalance() {
        return balance;
    }

    /**
     Basic getter for retrieving Profile Holder object.
     @return Profile holder object.
     */
    public Profile getHolder() {
        return holder;
    }

    /**
     Basic setter for updating and setting the new balance value.
     @param newBalance setting the newBalance to the balance variable
     */
    public void setBalance(double newBalance) {
        balance = newBalance;
    }

    /**
     abstract declaration to create subclasses custom string representations.
     @return a string that represents specific account type's state,holder's name, account type, balance.
     */
    public abstract String toString();

    /**
     abstract declaration should implement own comparison logic. Compare two accounts based on various criteria.
     @param obj which is the other account obj which is participating in the comparison.
     @return a int that represents the comparison between various attributes of each account object.
     */
    public abstract int compareTo(Account obj);
}