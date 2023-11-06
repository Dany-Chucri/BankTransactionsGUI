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

public class TransactionManagerController {

    private final AccountDatabase accountDatabase; // The Database structure to hold bank accounts

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
    private TextArea textArea;

    @FXML
    private ToggleGroup AccountType1, AccountType2, Campus;

    @FXML
    private Group campuses;

    @FXML
    private TextField firstName1, lastName1, initialDeposit, firstName2, lastName2, amount;

    @FXML
    private DatePicker dob1, dob2;

    @FXML
    private RadioButton checking1, checking2, savings1, savings2, collegeChecking1, collegeChecking2,
            moneyMarket1, moneyMarket2, camden, nb, newark;

    @FXML
    private CheckBox loyalty;

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

    @FXML
    void enableLoyalty() {
        if (loyalty.isDisabled()) {
            loyalty.setDisable(false);
        }
        disableCampus();
    }

    @FXML
    void disableLoyalty() {
        if (!loyalty.isDisabled()) {
            loyalty.setDisable(true);
        }
        disableCampus();
    }

    @FXML
    void enableCampus() {
        if (campuses.isDisabled()) {
            campuses.setDisable(false);
        }
    }

    @FXML
    void disableCampus() {
        if (!campuses.isDisabled()) {
            campuses.setDisable(true);
        }
    }

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
            RadioButton selectedRadioButton = (RadioButton) Campus.getSelectedToggle();
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

    private int bankType(String entry) {
        return switch (entry) {
            case "C" -> 1;
            case "CC" -> 2;
            case "MM" -> 3;
            case "S" -> 4;
            default -> 5;
        };
    }

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

    @FXML
    void printSorted() {
        accountDatabase.printSorted(textArea);

    }

    @FXML
    void printUpdatedBalances() {
        accountDatabase.printUpdatedBalances(textArea);
    }

    @FXML
    void printFeesAndInterests() {
        accountDatabase.printFeesAndInterests(textArea);
    }
}