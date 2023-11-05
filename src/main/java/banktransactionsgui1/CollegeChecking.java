package banktransactionsgui1;

import java.text.DecimalFormat;

/**
 Implements methods for calculating monthly interests and fees specific to the characteristics of a college checking account
 Custom String representations to sort accounts.
 @author Dany Chucri, Madhur Nutulapati
 */
public class CollegeChecking extends Checking {
    private final Campus campus; //campus code 0 - New Brunswick, 1 - Newark, 2 - Camden

    private static final double INT_RATE = 0.01; //1%
    private static final double MONTHLY_FEE = 0;

    /**
     Creates an instance of Checking.
     @param holder The profile class object holder.
     @param balance the bank balance given by the user.
     @param campus indicating 0 for NEW_BRUNSWICK, 1 for NEWARK, 2 for Camden.
     */
    public CollegeChecking(Profile holder, double balance, Campus campus){
            super(holder,balance);
            this.campus = campus;
    }

    /**
     Basic getter that retrieves the campus code.
     @return Campus object indicating 0 for New Brunswick 1 for Newark 2 Camden
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     Calculates monthly interest with respect to college checking account criteria.
     @return the balance with monthly interest
     */
    @Override
    public double monthlyInterest() {
        return balance * (INT_RATE/12);
    }

    /**
     Calculates monthly fee with respect to college checking account criteria.
     @return the monthly fee or no fee if balance greater than or equal to 1000
     */
    @Override
    public double monthlyFee() {
        if (balance >= 1000.0) {
            return 0.0;
        }
        else {
            return MONTHLY_FEE;
        }
    }

    /**
     method formats and returns a string representation of a college checking account,
     including the holder's name, date of birth, and balance.
     @return formatted string
     */
    @Override
    public String toString() {
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        String writtenBalance = formatter.format(balance);
        return "College Checking::" + holder.getFname() + " " + holder.getLname() + " " + holder.getDOB() + "::Balance " + writtenBalance + "::" + campus.toString();
    }

    /**
     Compares two college checking accounts based on their account type.
     @param obj is the other account obj being compared.
     @return int indicating the comparison = 0 if equal and negative value if not equal.
     */
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