package com.example.banktransactionsgui;

import java.text.DecimalFormat;

/**
 Implements methods for calculating monthly interests and fees specific to the characteristics of a money market account
 Custom String representations to sort accounts.
 Inherited from Savings
 @author Dany Chucri, Madhur Nutulapati
 */
public class MoneyMarket extends Savings{
    int withdrawal; //number of withdrawals
    private static final double MONTHLY_FEE = 25.0;
    private static final double INT_RATE = 0.045;
    private static final double LOYALTY_INT_RATE = 0.0475;
    /**
     Creates an instance of Money Market.
     @param holder The profile class object holder.
     @param balance the bank balance given by the user.
     */

    public MoneyMarket(Profile holder, double balance){
        super(holder, balance, true);
        this.withdrawal = 0;
    }

    /**
     Basic getters to retrieve withdrawal count.
     @return withdrawals
     */
    public int getWithdrawals() {
        return withdrawal;
    }

    /**
     Basic setter to set/update isLoyal value to the newLoyalty.
     @param newLoyalty the new loyalty value.
     */
    public void setLoyalty(boolean newLoyalty) {
        isLoyal = newLoyalty;
    }

    /**
     Basic helper method to increment the withdrawal
     */
    public void incWithdrawal() {
        withdrawal++;
    }

    /**
     Basic setter to set withdrawal.
     @param x the amount you want to set withdrawal to .
     */
    public void setWithdrawal (int x) {
        withdrawal = x;
    }

    /**
     Calculates monthly interest with respect to Money Market account criteria.
     @return the balance with monthly interest
     */
    @Override
    public double monthlyInterest() {
        double monthlyInt;
        if (isLoyal) {
            monthlyInt = balance * (LOYALTY_INT_RATE / 12);
        }
        else {
            monthlyInt = balance * (INT_RATE / 12);
        }
        return monthlyInt;
    }

    /**
     Calculates monthly fee with respect to Money Market account criteria.
     @return the monthly fee if your balance is less than 2000 and 3 + withdrawals monthly fee +10.
     Otherwise, Monthly fee if balance < 2000.0
     Or if withdrawal more than 3 times than return 10.0 or return 0.0
     */
    @Override
    public double monthlyFee(){
        if (balance < 2000.0 && withdrawal > 3) {
            return MONTHLY_FEE + 10.0;
        }
        else if (balance < 2000.0) {
            return MONTHLY_FEE;
        }
        else if (withdrawal > 3) {
            return 10.0;
        }
        return 0.0;
    }

    /**
     method formats and returns a string representation of a Money Market account,
     including the holder's name, date of birth, and balance.
     @return formatted string
     */
    @Override
    public String toString() {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        String writtenBalance = formatter.format(balance);
        String loyalty = "";
        if (isLoyal) loyalty += "::is loyal";
        return "Money Market::Savings::" + holder.getFname() + " " + holder.getLname() + " " + holder.getDOB() + "::Balance " + writtenBalance + loyalty + "::withdrawal: " + withdrawal;
    }

    /**
     Compares two Money Market accounts based on their account type.
     @param obj is the other account obj being compared.
     @return int indicating the comparison = 0 if equal and negative value if not equal.
     */
    @Override
    public int compareTo(Account obj) {
        String thisAccountType = this.toString();
        String oAccType = obj.toString();
        thisAccountType = thisAccountType.substring(0, thisAccountType.indexOf("::"));
        oAccType = oAccType.substring(0, oAccType.indexOf("::"));
        int accTypeCompare = thisAccountType.compareTo(oAccType);
        if (accTypeCompare != 0) {
            return accTypeCompare;
        }
        return this.holder.compareTo(obj.holder);
    }
}