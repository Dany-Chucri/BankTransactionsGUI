package banktransactionsgui1;
/**
 represents a user profile with first name, last name, and date of birth attributes.
 Implements compareTo method to compare
 @author Dany Chucri, Madhur Nutulapati
 */
public class Profile implements Comparable<Profile> {
    private final String fname; // First name of the account holder
    private final String lname; // Last name of the account holder
    private final Date dob; // Date of birth of the account holder

    /**
     Creates an instance of Profile.
     @param fname first name of account holder
     @param lname last name of account holder
     @param dob date of birth of account holder
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     Basic Getter to retrieve the first name
     @return first Name
     */
    public String getFname() {
        return fname;
    }

    /**
     Basic Getter to retrieve the last name
     @return last Name
     */
    public String getLname() {
        return lname;
    }

    /**
     Basic Getter to retrieve the Date object -> Date of Birth
     @return Date object (for date of birth)
     */
    public Date getDOB() {
        return dob;
    }

    /**
     Compares two profile accounts based on their account type.
     @param obj is the other profile obj being compared.
     @return int indicating the comparison = 0 if equal and negative value if not equal.
     */
    @Override
    public int compareTo(Profile obj) {
        int lastNCompare = this.lname.compareToIgnoreCase(obj.lname);
        if (lastNCompare != 0) {
            return lastNCompare;
        }
        int firstNCompare = this.fname.compareToIgnoreCase(obj.fname);
        if (firstNCompare != 0) {
            return firstNCompare;
        }
        return this.dob.compareTo(obj.dob);
    }
}