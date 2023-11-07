package banktransactionsgui1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Controller for the graphical user interface to process input for the Account Database.
 * Runs via "TransactionManagerMain".
 * @author Dany Chucri, Madhur Nutulapati
 */
public class TransactionManagerController {

    private final AccountDatabase accountDatabase; // The Database structure to hold bank accounts

    /**
     * Instantiates the TransactionManager using Account Database.
     */
    public TransactionManagerController() {
        this.accountDatabase = new AccountDatabase();
    }

    private static final int OPEN_INDICATION = 1;
    private static final int CLOSE_INDICATION = 2;
    private static final int DEPOSIT_INDICATION = 3;
    private static final int WITHDRAW_INDICATION = 4;
    private static final int INVALID_DATE = 1;
    private static final int NO_TODAY_NO_FUTURE = 2;


    @FXML
    private TextArea textArea; // The output text area of the GUI.

    @FXML
    private ToggleGroup AccountType1, AccountType2, Campus; // Toggle groups for radio buttons.

    @FXML
    private Group campuses; // Campuses radio button group.

    @FXML
    private TextField firstName1, lastName1, initialDeposit, firstName2, lastName2, amount; // Text Fields for inputting data.

    @FXML
    private DatePicker dob1, dob2; // Date fields for both tabs.

    @FXML
    private RadioButton checking1, checking2, savings1, savings2, collegeChecking1, collegeChecking2,
            moneyMarket1, moneyMarket2, camden, nb, newark; // Various radio buttons.

    @FXML
    private CheckBox loyalty; // Checkbox for a loyal customer on the Open/Close tab.

    /**
     * Clears inputs from the Open/Close tab.
     */
    @FXML
    void clearFirst() {
        firstName1.clear();
        lastName1.clear();
        dob1.getEditor().clear();
        initialDeposit.clear();
        checking1.setSelected(false);
        collegeChecking1.setSelected(false);
        savings1.setSelected(false);
        moneyMarket1.setSelected(false);
        camden.setDisable(true);
        nb.setDisable(true);
        newark.setDisable(true);
    }

    /**
     * Clears inputs from the Deposit/Withdraw tab.
     */
    @FXML
    void clearSecond() {
        firstName2.clear();
        lastName2.clear();
        dob2.getEditor().clear();
        amount.clear();
        checking2.setSelected(false);
        collegeChecking2.setSelected(false);
        savings2.setSelected(false);
        moneyMarket2.setSelected(false);
    }

    /**
     * Enables the "Loyal Customer" option.
     * Also disables the "Campus" group.
     */
    @FXML
    void enableLoyalty() {
        if (loyalty.isDisabled()) {
            loyalty.setDisable(false);
        }
        if (!campuses.isDisabled()) {
            campuses.setDisable(true);
        }
    }

    /**
     * Disables the "Loyal Customer" option.
     * Also disables the "Campus" group.
     */
    @FXML
    void disableLoyalty() {
        if (!loyalty.isDisabled()) {
            loyalty.setDisable(true);
        }
        if (!campuses.isDisabled()) {
            campuses.setDisable(true);
        }
    }


    /**
     * Enables the "Campus" group.
     * Also disables the "Loyal Customer" option.
     */
    @FXML
    void enableCampus() {
        if (campuses.isDisabled()) {
            campuses.setDisable(false);
        }
        if (!loyalty.isDisabled()) {
            loyalty.setDisable(true);
        }
    }

    /**
     * Disables the "Campus" group.
     * Also disables the "Loyal Customer" option.
     */
    @FXML
    void disableCampus() {
        if (!campuses.isDisabled()) {
            campuses.setDisable(true);
        }
        if (!loyalty.isDisabled()) {
            loyalty.setDisable(true);
        }
    }

    /**
     To create Checking object to perform the respective operation of opening,closing, depositing, or withdrawing.
     @param addProfile the Profile object
     @param balance The user balance information
     @param operation Indicates either open, close, Deposit, or Withdraw
     */
    @FXML
    private void createChecking(Profile addProfile,double balance,int operation){
        Checking addAccount = new Checking(addProfile,balance);
        if(operation == OPEN_INDICATION){
            if (accountDatabase.open(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) opened.\n");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) is already in the database.\n");
        } else if (operation==CLOSE_INDICATION){
            if (accountDatabase.close(addAccount)) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) has been closed.\n");
            }
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) is not in the database.\n");
        } else if (operation==DEPOSIT_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) is not in the database.\n");
            } else {
                accountDatabase.deposit(addAccount);
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) Deposit - balance updated.\n");
            }
        } else if (operation==WITHDRAW_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) is not in the database.\n");
            }
            else {
                if (accountDatabase.withdraw(addAccount))
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) Withdraw - balance updated.\n");
                else
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) Withdraw - insufficient fund.\n");
            }
        } else {
            textArea.appendText("\n");
        }
    }

    /**
     To create Savings object to perform the respective operation of opening,closing, depositing, or withdrawing.
     @param addProfile the Profile object
     @param balance The user balance information
     @param loyal indicating whether holder is Loyal or not. 1 Being loyal and otherwise being not loyal.
     @param operation Indicates either open, close, Deposit, or Withdraw
     */
    @FXML
    private void createSavings(Profile addProfile,double balance, boolean loyal, int operation){
        Savings addAccount = new Savings(addProfile, balance, loyal);
        if(operation == OPEN_INDICATION){
            if(accountDatabase.open(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) opened.\n");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) is already in the database.\n");
        } else if(operation==CLOSE_INDICATION){
            if(accountDatabase.close(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) has been closed.\n");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) is not in the database.\n");
        } else if (operation==DEPOSIT_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) is not in the database.\n");
            } else {
                accountDatabase.deposit(addAccount);
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) Deposit - balance updated.\n");
            }
        } else if (operation==WITHDRAW_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) is not in the database.\n");
            }
            else {
                if (accountDatabase.withdraw(addAccount))
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) Withdraw - balance updated.\n");
                else
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) Withdraw - insufficient fund.\n");
            }
        } else{
            textArea.appendText("The account is already on the database.\n");
        }
    }

    /**
     To create College Checking object to perform the respective operation of opening,closing, depositing, or withdrawing.
     @param addProfile the Profile object
     @param balance The user balance information
     @param code The campus code - > 0 - New Brunswick; 1 - Newark ; 2 - Camden
     @param operation Indicates either open, close, Deposit, or Withdraw
     */
    @FXML
    private void createCollegeChecking(Profile addProfile,double balance, Campus code,int operation){
        CollegeChecking addAccount = new CollegeChecking(addProfile,balance,code);
        if(operation==OPEN_INDICATION){
            if(accountDatabase.open(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) opened.\n");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) is already in the database.\n");
        } else if(operation==CLOSE_INDICATION){
            if(accountDatabase.close(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) has been closed.\n");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) is not in the database.\n");
        } else if (operation==DEPOSIT_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) is not in the database.\n");

            } else {
                accountDatabase.deposit(addAccount);
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) Deposit - balance updated.\n");
            }
        } else if (operation==WITHDRAW_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) is not in the database.\n");
            }
            else {
                if (accountDatabase.withdraw(addAccount))
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) Withdraw - balance updated.\n");
                else
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) Withdraw - insufficient fund.\n");
            }
        } else{
            textArea.appendText("The account is already on the database.\n");
        }
    }

    /**
     To create Money Market object to perform the respective operation of opening,closing, depositing, or withdrawing.
     @param addProfile the Profile object
     @param balance The user balance information
     @param operation Indicates either open, close, Deposit, or Withdraw
     */
    @FXML
    private void createMoneyMarket(Profile addProfile,double balance,int operation){
        MoneyMarket addAccount = new MoneyMarket(addProfile,balance);
        if( operation == OPEN_INDICATION ){
            if (balance < 2000) {
                textArea.appendText("Minimum of $2000 to open a Money Market account.\n");
            }
            else if(accountDatabase.open(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) opened.\n");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) is already in the database.\n");
        } else if(operation==CLOSE_INDICATION){
            if(accountDatabase.close(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) has been closed.\n");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) is not in the database.\n");
        } else if (operation==DEPOSIT_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) is not in the database.\n");
            } else {
                accountDatabase.deposit(addAccount);
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) Deposit - balance updated.\n");
            }
        } else if (operation==WITHDRAW_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) is not in the database.\n");
            }
            else {
                if (accountDatabase.withdraw(addAccount))
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) Withdraw - balance updated.\n");
                else
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) Withdraw - insufficient fund.\n");
            }
        } else{
            textArea.appendText("The account is already on the database.\n");
        }
    }

    /**
     * Will check the validity of a balance when called.
     * @param token An array of tokens from the command-line arguments.
     * @param type deposit or withdrawal
     */
    private double checkBalance(String token, String type) {
        double balanceAmount;
        try {
            balanceAmount = Double.parseDouble(token);
        } catch (Exception e) {
            textArea.appendText("Not a valid amount.\n");
            return -1;
        }
        if (balanceAmount <= 0){
            String s;
            if (type.equals("Deposit")) s = "Deposit";
            else s = "Withdraw";
            textArea.appendText(s + " - amount cannot be 0 or negative.\n");
            return -1;
        }
        return balanceAmount;
    }

    /**
     * Opens an account based on the inputs provided in the Open/Close tab.
     */
    @FXML
    void open() {
        String firstName = firstName1.getText(); if (firstName.isEmpty()) {textArea.appendText("Missing data for opening an account.\n"); return;}
        String lastName = lastName1.getText(); if (lastName.isEmpty()) {textArea.appendText("Missing data for opening an account.\n"); return;}
        String dob = dob1.getEditor().getText(); if (dob.isEmpty()) {textArea.appendText("Missing data for opening an account.\n"); return;}
        String initialDepositText = initialDeposit.getText(); if (initialDepositText.isEmpty()) {textArea.appendText("Missing data for opening an account.\n"); return;}
        double initialDeposit;
        try { initialDeposit = Double.parseDouble(initialDepositText); }
        catch (Exception e) {textArea.appendText("Not a valid amount.\n"); return;}
        int indication = 0;
        if(collegeChecking1.isSelected()) { indication = 2;}
        Date dateInput = createDate(dob, indication);
        if (dateInput == null) {return;}
        if (initialDeposit <= 0) {
            textArea.appendText("Initial deposit cannot be 0 or negative.\n");
            return;
        }
        if (checking1.isSelected()) {
            createChecking(new Profile(firstName, lastName, dateInput), initialDeposit, OPEN_INDICATION);
        } else if (savings1.isSelected()) {
            boolean isLoyal = loyalty.isSelected();
            createSavings(new Profile(firstName, lastName, dateInput),initialDeposit,isLoyal,OPEN_INDICATION);
        } else if (collegeChecking1.isSelected()) {
            RadioButton selectedRadioButton = (RadioButton) Campus.getSelectedToggle(); if (selectedRadioButton == null) {textArea.appendText("You must select a campus.\n"); return;}
            String campusText = selectedRadioButton.getText();
            banktransactionsgui1.Campus campus = switch (campusText) {
                case "NB" -> banktransactionsgui1.Campus.NEW_BRUNSWICK;
                case "Newark" -> banktransactionsgui1.Campus.NEWARK;
                case "Camden" -> banktransactionsgui1.Campus.CAMDEN;
                default -> null;
            };
            createCollegeChecking(new Profile(firstName, lastName, dateInput),initialDeposit,campus,OPEN_INDICATION);
        } else if (moneyMarket1.isSelected()) {
            createMoneyMarket(new Profile(firstName, lastName, dateInput),initialDeposit,OPEN_INDICATION);
        }
        else {
            textArea.appendText("Missing data for opening an account.\n");
        }
    }

    /**
     * Closes an account based on the inputs provided in the Open/Close tab.
     */
    @FXML
    void close() {
        String firstName = firstName1.getText(); if (firstName.isEmpty()) {textArea.appendText("Missing data for closing an account.\n"); return;}
        String lastName = lastName1.getText(); if (lastName.isEmpty()) {textArea.appendText("Missing data for closing an account.\n"); return;}
        int indication = 0;
        String dob = dob1.getEditor().getText(); if (dob.isEmpty()) {textArea.appendText("Missing data for closing an account.\n"); return;}
        Date dateInput = createDate(dob, indication);
        if (dateInput == null) {return;}

        if (checking1.isSelected()) {
            createChecking(new Profile(firstName, lastName, dateInput), 0, CLOSE_INDICATION);
        } else if (savings1.isSelected()) {
            createSavings(new Profile(firstName, lastName, dateInput),0,false,CLOSE_INDICATION);
        } else if (collegeChecking1.isSelected()) {
            createCollegeChecking(new Profile(firstName, lastName, dateInput),0, banktransactionsgui1.Campus.NEW_BRUNSWICK,CLOSE_INDICATION);
        } else if (moneyMarket1.isSelected()) {
            createMoneyMarket(new Profile(firstName, lastName, dateInput),0,CLOSE_INDICATION);
        }
        else {
            textArea.appendText("Missing data for closing an account.\n");
        }
    }

    /**
     * Deposits into an account based on the inputs provided in the Deposit/Withdraw tab.
     */
    @FXML
    void deposit() {
        String firstName = firstName2.getText(); if (firstName.isEmpty()) {textArea.appendText("Missing data for depositing into an account.\n"); return;}
        String lastName = lastName2.getText(); if (lastName.isEmpty()) {textArea.appendText("Missing data for depositing into an account.\n"); return;}
        int indication = 0;
        String dob = dob2.getEditor().getText(); if (dob.isEmpty()) {textArea.appendText("Missing data for depositing into an account.\n"); return;}
        Date dateInput = createDate(dob, indication);
        if (dateInput == null) {return;}
        String depositAmountTxt = amount.getText(); if (depositAmountTxt.isEmpty()) {textArea.appendText("Missing data for depositing into an account.\n"); return;}
        double depositAmount = checkBalance(depositAmountTxt, "Deposit");
        if (depositAmount <= 0) return;

        if (checking2.isSelected()) {
            createChecking(new Profile(firstName, lastName, dateInput), depositAmount, DEPOSIT_INDICATION);
        } else if (savings2.isSelected()) {
            createSavings(new Profile(firstName, lastName, dateInput),depositAmount,false, DEPOSIT_INDICATION);
        } else if (collegeChecking2.isSelected()) {
            createCollegeChecking(new Profile(firstName, lastName, dateInput),depositAmount, banktransactionsgui1.Campus.NEW_BRUNSWICK, DEPOSIT_INDICATION);
        } else if (moneyMarket2.isSelected()) {
            createMoneyMarket(new Profile(firstName, lastName, dateInput),depositAmount, DEPOSIT_INDICATION);
        }
        else {
            textArea.appendText("Missing data for depositing into an account.\n");
        }
    }

    /**
     * Withdraws from an account based on the inputs provided in the Deposit/Withdraw tab.
     */
    @FXML
    void withdraw() {
        String firstName = firstName2.getText(); if (firstName.isEmpty()) {textArea.appendText("Missing data for withdrawing from an account.\n"); return;}
        String lastName = lastName2.getText(); if (lastName.isEmpty()) {textArea.appendText("Missing data for withdrawing from an account.\n"); return;}
        int indication = 0;
        String dob = dob2.getEditor().getText(); if (dob.isEmpty()) {textArea.appendText("Missing data for withdrawing from an account.\n"); return;}
        Date dateInput = createDate(dob, indication);
        if (dateInput == null) {return;}
        String withdrawAmtTxt = amount.getText(); if (withdrawAmtTxt.isEmpty()) {textArea.appendText("Missing data for depositing into an account.\n"); return;}

        double withdrawAmt = checkBalance(withdrawAmtTxt, "Withdraw");
        if (withdrawAmt <= 0) return;

        if (checking2.isSelected()) {
            createChecking(new Profile(firstName, lastName, dateInput), withdrawAmt, WITHDRAW_INDICATION);
        } else if (savings2.isSelected()) {
            createSavings(new Profile(firstName, lastName, dateInput),withdrawAmt,false,WITHDRAW_INDICATION);
        } else if (collegeChecking2.isSelected()) {
            createCollegeChecking(new Profile(firstName, lastName, dateInput),withdrawAmt, banktransactionsgui1.Campus.NEW_BRUNSWICK,WITHDRAW_INDICATION);
        } else if (moneyMarket2.isSelected()) {
            createMoneyMarket(new Profile(firstName, lastName, dateInput),withdrawAmt,WITHDRAW_INDICATION);
        }
        else {
            textArea.appendText("Missing data for withdrawing from an account.\n");
        }
    }

    /**
     * Loads a list of accounts to be inputted into the Account Database automatically.
     * This provided text file must be written according to the specifications of "bankAccounts.txt".
     * Performs minimal error checking as requested by the specifications.
     * @throws FileNotFoundException When a provided file does not exist or is inaccessible.
     */
    @FXML
    void loadAccountsFromFile() throws FileNotFoundException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for Importing Account Set");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
        if (sourceFile == null) {
            return;
        }
        Scanner S = new Scanner(sourceFile);
        while(S.hasNextLine()) {
            String fullCommand = S.nextLine().trim();   //read + trim
            if(fullCommand.isEmpty())   //blank line
                continue;
            String[] token = fullCommand.split(",");
            int key = bankType(token[0]);
            Date dateInput = createDate(token[3], key);
            if (dateInput == null) return;
            Profile addProfile = new Profile(token[1], token[2], dateInput);
            loaderOpener(token, key, addProfile);
        }
    }

    /**
     * Helper method for loadAccountsFromFile().
     * @param token A line of tokens read from the input file.
     * @param key An indication of the bank account type.
     * @param addProfile Profile object to create an instance of Account with.
     */
    private void loaderOpener(String[] token, int key, Profile addProfile) {
        double balance;
        try { balance = Double.parseDouble(token[4]);
        } catch (Exception e) {
            textArea.appendText("Not a valid amount.\n");
            return;
        }
        if (balance <= 0) {
            textArea.appendText("Initial deposit cannot be 0 or negative.\n");
            return;
        }
        if (key == 1)
            createChecking(addProfile, balance, OPEN_INDICATION);
        if (key == 2) {
            String phraseLoc = checkCampusCode(Integer.parseInt(token[5]));
            if (phraseLoc.equals("INVALID")) {
                textArea.appendText("Invalid campus code.\n");
            }
            else createCollegeChecking(addProfile, balance, banktransactionsgui1.Campus.valueOf(phraseLoc), OPEN_INDICATION);
        }
        else if (key == 3)
            createMoneyMarket(addProfile, balance, OPEN_INDICATION);
        else if (key == 4)
            createSavings(addProfile, balance, !(Integer.parseInt(token[5]) == 0), OPEN_INDICATION);
    }

    /**
     Helper method to retrieve the type of bank, given the token string entry
     @param entry String entry of the token.
     @return int 1 - for checking; 2 for College checking; 3 for Money Market; 4 for Savings
     */
    private int bankType(String entry) {
        return switch (entry) {
            case "C" -> 1;
            case "CC" -> 2;
            case "MM" -> 3;
            case "S" -> 4;
            default -> 5;
        };
    }

    /**
     Helper method to give us the respective phrase for the enum Campus class.
     @param campCode 0-New Brunswick; 1-Newark;2-Camden
     @return string returning the phrase for the respective campCode integer
     */
    private String checkCampusCode(int campCode){
        if(campCode==0){
            return "NEW_BRUNSWICK";
        } else if (campCode==1) {
            return "NEWARK";
        } else if (campCode==2) {
            return "CAMDEN";
        }
        else{
            return "INVALID";
        }
    }

    /**
     * Instantiates a Date object to be used for the creation of an Account.
     * Performs error checks on the validity of a date.
     * @param date A String token in the form of "xx/xx/xxxx".
     * @param collegeIndication indicates whether it is a college checking account to perform age check.
     * @return The Date object to be used.
     */
    private Date createDate(String date,int collegeIndication) {
        Date addDate;
        try {
            addDate = new Date(date);
        }
        catch (Exception e) {
            textArea.appendText("Not a valid input for date!\n");
            return null;

        }
        if(addDate.isValid() == INVALID_DATE){
            textArea.appendText("DOB invalid: " + date + " not a valid calendar date!\n");
            return null;
        } else if (addDate.isValid() == NO_TODAY_NO_FUTURE) {
            textArea.appendText("DOB invalid: " + date + " cannot be today or a future day.\n");
            return null;
        } else if(collegeIndication == 2 && addDate.checkAge() >= 24){
            textArea.appendText("DOB invalid: " + date + " over 24.\n");
            return null;
        } else if (addDate.checkAge()<16) {
            textArea.appendText("DOB invalid: " + date + " under 16.\n");
            return null;
        }
        return addDate;
    }

    /**
     * Prints sorted accounts from accountDatabase
     */
    @FXML
    void printSorted() {
        accountDatabase.printSorted(textArea);

    }

    /**
     * Updates the information of accounts in the database based on their fees and interests.
     * Then prints the updated Account Database.
     */
    @FXML
    void printUpdatedBalances() {
        accountDatabase.printUpdatedBalances(textArea);
    }

    /**
     * Prints the monthly fees and interest rates of the accounts in the database.
     */
    @FXML
    void printFeesAndInterests() {
        accountDatabase.printFeesAndInterests(textArea);
    }
}