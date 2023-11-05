package banktransactionsgui1;

import java.text.DecimalFormat;

/**
 Implements methods for calculating monthly interests and fees specific to the characteristics of a Savings account
 Custom String representations to sort accounts.
 @author Dany Chucri, Madhur Nutulapati
 */
public class Savings extends Account {
    private static final double INT_RATE = 0.04;
    private static final double LOYALTY_INT_RATE = 0.0425;
    private static final double MONTHLY_FEE = 25.0;
    protected boolean isLoyal; // loyal customer status

    /**
     Creates an instance of Savings.
     @param holder The profile class object holder.
     @param balance the bank balance given by the user.
     @param isLoyal true indicating loyal, otherwise false.
     */
    public Savings(Profile holder, double balance, boolean isLoyal) {
        super(holder, balance);
        this.isLoyal = isLoyal;
    }

    /**
     * Basic getter for the loyalty status of the account
     * @return true if loyal, false if not
     */
    public boolean getLoyalty() {
        return isLoyal;
    }

    /**
     Calculates monthly interest with respect to savings account criteria.
     @return the balance with monthly interest
     */
    @Override
    public double monthlyInterest(){
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
     Calculates monthly fee with respect to Savings account criteria.
     @return the monthly fee or no fee if balance greater than or equal to 500
     */
    @Override
    public double monthlyFee(){
        if (balance >= 500.0) {
            return 0.0;
        }
        else{
            return MONTHLY_FEE;
        }
    }

    /**
     method formats and returns a string representation of a savings account,
     including the holder's name, date of birth, and balance.
     @return formatted string
     */
    @Override
    public String toString() {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        String writtenBalance = formatter.format(balance);
        String loyalty = "";
        if (isLoyal) loyalty += "::is loyal";
        return "Savings::" + holder.getFname() + " " + holder.getLname() + " " + holder.getDOB() + "::Balance " + writtenBalance + loyalty;
    }

    /**
     Compares two Savings accounts based on their account type.
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