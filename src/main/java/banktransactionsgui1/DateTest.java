package banktransactionsgui1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void invalid_Calendar_Month_Returns_1() {
        Date testDate = new Date("13/01/2003");
        assertEquals(1, testDate.isValid());
    }

    @Test
    void given_Day_Is_31_For_A_30_Day_Month() {
        Date testDate = new Date("11/31/2003");
        assertEquals(1, testDate.isValid());
    }

    @Test
    void invalid_Future_Date() {
        Date testDate = new Date("04/24/2024");
        assertEquals(2, testDate.isValid());
    }

    @Test
    void today_Is_An_Invalid_Date() {
        Date testDate = new Date();
        assertEquals(2, testDate.isValid());
    }

    @Test
    void february_2_On_A_Non_Leap_year() {
        Date testDate = new Date("02/29/1999");
        assertEquals(1, testDate.isValid());
    }

    @Test
    void valid_Date_of_Birth() {
        Date testDate = new Date("04/24/2003");
        assertEquals(0, testDate.isValid());
    }

    @Test
    void valid_Leap_Year() {
        Date testDate = new Date("02/29/2000");
        assertEquals(0, testDate.isValid());
    }
}