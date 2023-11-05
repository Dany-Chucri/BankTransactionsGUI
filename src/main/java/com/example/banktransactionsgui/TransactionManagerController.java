package com.example.banktransactionsgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;

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
    private Button open, close, clear, deposit, withdraw, printAll, loadAccounts, printFeesInterests, printUpdate;

    @FXML
    private RadioButton checking1, checking2, savings1, savings2, collegeChecking1, collegeChecking2,
            moneyMarket1, moneyMarket2, camden, nb, newark;

    @FXML
    private CheckBox loyalty;

    @FXML
    void clearFirst(ActionEvent event) {
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
        loyalty.setDisable(true);
    }

    @FXML
    void clearSecond(ActionEvent event) {
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
    void dateValidity(ActionEvent event) {

    }

    @FXML
    void enableLoyalty(ActionEvent event) {
        loyalty.disableProperty().bind(
                AccountType1.selectedToggleProperty().isNotEqualTo(moneyMarket1).and(
                        AccountType1.selectedToggleProperty().isNotEqualTo(savings1)));
        disableCampus(event);
    }

    @FXML
    void enableCampus(ActionEvent event) {
        if (campuses.isDisabled()) {
            campuses.setDisable(false);
        }
    }

    @FXML
    void disableCampus(ActionEvent event) {
        if (!campuses.isDisabled()) {
            campuses.setDisable(true);
        }
    }
    @FXML
    private void createChecking(Profile addProfile,double balance,int operation){
        Checking addAccount = new Checking(addProfile,balance);
        if(operation == OPEN_INDICATION){
            if (accountDatabase.open(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) opened.");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) is already in the database.");
        } else if (operation==CLOSE_INDICATION){
            if (accountDatabase.close(addAccount)) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) has been closed.");
            }
            else
                System.out.println(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) is not in the database.");
        } else if (operation==DEPOSIT_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) is not in the database.");
            } else {
                accountDatabase.deposit(addAccount);
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) Deposit - balance updated.");
            }
        } else if (operation==WITHDRAW_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) is not in the database.");
            }
            else {
                if (accountDatabase.withdraw(addAccount))
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) Withdraw - balance updated.");
                else
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(C) Withdraw - insufficient fund.");
            }
        } else {
            textArea.appendText("The account is already on the database.");
        }
    }
    @FXML
    private void createSavings(Profile addProfile,double balance, boolean loyal,int operation){
//        boolean loyalKey = loyal == 1;
        Savings addAccount = new Savings(addProfile, balance, loyal);
        if(operation == OPEN_INDICATION){
            if(accountDatabase.open(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) opened.");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) is already in the database.");
        } else if(operation==CLOSE_INDICATION){
            if(accountDatabase.close(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) has been closed.");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) is not in the database.");
        } else if (operation==DEPOSIT_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) is not in the database.");
            } else {
                accountDatabase.deposit(addAccount);
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) Deposit - balance updated.");
            }
        } else if (operation==WITHDRAW_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) is not in the database.");
            }
            else {
                if (accountDatabase.withdraw(addAccount))
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) Withdraw - balance updated.");
                else
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(S) Withdraw - insufficient fund.");
            }
        } else{
            textArea.appendText("The account is already on the database.");
        }
    }
    @FXML
    private void createCollegeChecking(Profile addProfile,double balance, Campus code,int operation){
        CollegeChecking addAccount = new CollegeChecking(addProfile,balance,code);
        if(operation==OPEN_INDICATION){
            if(accountDatabase.open(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) opened.");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) is already in the database.");
        } else if(operation==CLOSE_INDICATION){
            if(accountDatabase.close(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) has been closed.");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) is not in the database.");
        } else if (operation==DEPOSIT_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) is not in the database.");

            } else {
                accountDatabase.deposit(addAccount);
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) Deposit - balance updated.");
            }
        } else if (operation==WITHDRAW_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) is not in the database.");
            }
            else {
                if (accountDatabase.withdraw(addAccount))
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) Withdraw - balance updated.");
                else
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(CC) Withdraw - insufficient fund.");
            }
        } else{
            textArea.appendText("The account is already on the database.");
        }
    }
    @FXML
    private void createMoneyMarket(Profile addProfile,double balance,int operation){
        MoneyMarket addAccount = new MoneyMarket(addProfile,balance);
        if( operation == OPEN_INDICATION ){
            if (balance < 2000) {
                textArea.appendText("Minimum of $2000 to open a Money Market account.");
            }
            else if(accountDatabase.open(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) opened.");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) is already in the database.");
        } else if(operation==CLOSE_INDICATION){
            if(accountDatabase.close(addAccount))
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) has been closed.");
            else
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) is not in the database.");
        } else if (operation==DEPOSIT_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) is not in the database.");
            } else {
                accountDatabase.deposit(addAccount);
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) Deposit - balance updated.");
            }
        } else if (operation==WITHDRAW_INDICATION) {
            if (!(accountDatabase.contains(addAccount))) {
                textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) is not in the database.");
            }
            else {
                if (accountDatabase.withdraw(addAccount))
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) Withdraw - balance updated.");
                else
                    textArea.appendText(addProfile.getFname() + " " + addProfile.getLname() + " " + addProfile.getDOB() + "(MM) Withdraw - insufficient fund.");
            }
        } else{
            textArea.appendText("The account is already on the database.");
        }
    }
    @FXML
    void open(ActionEvent event) {
        String firstName = firstName1.getText();
        String lastName = lastName1.getText();
        Date dob = new Date(dob1.getEditor().getText());
        String initialDepositText = initialDeposit.getText();
        double initialDeposit = Double.parseDouble(initialDepositText);

        if (checking1.isSelected()) {
            createChecking(new Profile(firstName, lastName, dob), initialDeposit, OPEN_INDICATION);
        } else if (savings1.isSelected()) {
            boolean isLoyal = loyalty.isSelected();
            createSavings(new Profile(firstName, lastName, dob),initialDeposit,isLoyal,OPEN_INDICATION);
//            newAccount = new Savings(new Profile(firstName, lastName, dob), initialDeposit, isLoyal);
        } else if (collegeChecking1.isSelected()) {
            RadioButton selectedRadioButton = (RadioButton) Campus.getSelectedToggle();
            String campusText = selectedRadioButton.getText();
            com.example.banktransactionsgui.Campus campus;
            if (campusText.equals(com.example.banktransactionsgui.Campus.NEW_BRUNSWICK.toString())) {
                campus = com.example.banktransactionsgui.Campus.NEW_BRUNSWICK;
            } else if (campusText.equals(com.example.banktransactionsgui.Campus.NEWARK.toString())) {
                campus = com.example.banktransactionsgui.Campus.NEWARK;
            } else if (campusText.equals(com.example.banktransactionsgui.Campus.CAMDEN.toString())) {
                campus = com.example.banktransactionsgui.Campus.CAMDEN;
            } else {
                campus = null;
            }
            createCollegeChecking(new Profile(firstName, lastName, dob),initialDeposit,campus,OPEN_INDICATION);
//            newAccount = new CollegeChecking(new Profile(firstName, lastName, dob), initialDeposit, campus);
        } else if (moneyMarket1.isSelected()) {
            createMoneyMarket(new Profile(firstName, lastName, dob),initialDeposit,OPEN_INDICATION);
//                newAccount = new MoneyMarket(new Profile(firstName, lastName, dob), initialDeposit);
        }
//        textArea.appendText("New account opened:\n" + newAccount.toString() + "\n");
    }
    @FXML
    void close(ActionEvent event) {
        String firstName = firstName1.getText();
        String lastName = lastName1.getText();
        Date dob = new Date(dob1.getEditor().getText());

        if (checking1.isSelected()) {
            createChecking(new Profile(firstName, lastName, dob), 0, CLOSE_INDICATION);
        } else if (savings1.isSelected()) {
            createSavings(new Profile(firstName, lastName, dob),0,false,CLOSE_INDICATION);
        } else if (collegeChecking1.isSelected()) {
            createCollegeChecking(new Profile(firstName, lastName, dob),0,com.example.banktransactionsgui.Campus.NEW_BRUNSWICK,CLOSE_INDICATION);
        } else if (moneyMarket1.isSelected()) {
            createMoneyMarket(new Profile(firstName, lastName, dob),0,CLOSE_INDICATION);
        }
    }
    @FXML
    void deposit(ActionEvent event) {
        String firstName = firstName2.getText();
        String lastName = lastName2.getText();
        Date dob = new Date(dob2.getEditor().getText());
        String depositAmountTxt = amount.getText();
        double depositAmount = Double.parseDouble(depositAmountTxt);

        if (checking1.isSelected()) {
            createChecking(new Profile(firstName, lastName, dob), depositAmount, DEPOSIT_INDICATION);
        } else if (savings1.isSelected()) {
            createSavings(new Profile(firstName, lastName, dob),depositAmount,false,DEPOSIT_INDICATION);
        } else if (collegeChecking1.isSelected()) {
            createCollegeChecking(new Profile(firstName, lastName, dob),depositAmount,com.example.banktransactionsgui.Campus.NEW_BRUNSWICK,DEPOSIT_INDICATION);
        } else if (moneyMarket1.isSelected()) {
            createMoneyMarket(new Profile(firstName, lastName, dob),depositAmount,DEPOSIT_INDICATION);
        }
    }
    @FXML
    void withdraw(ActionEvent event) {
        String firstName = firstName2.getText();
        String lastName = lastName2.getText();
        Date dob = new Date(dob2.getEditor().getText());
        String withdrawAmtTxt = amount.getText();
        double withdrawAmt = Double.parseDouble(withdrawAmtTxt);

        if (checking1.isSelected()) {
            createChecking(new Profile(firstName, lastName, dob), withdrawAmt, WITHDRAW_INDICATION);
        } else if (savings1.isSelected()) {
            createSavings(new Profile(firstName, lastName, dob),withdrawAmt,false,WITHDRAW_INDICATION);
        } else if (collegeChecking1.isSelected()) {
            createCollegeChecking(new Profile(firstName, lastName, dob),withdrawAmt,com.example.banktransactionsgui.Campus.NEW_BRUNSWICK,WITHDRAW_INDICATION);
        } else if (moneyMarket1.isSelected()) {
            createMoneyMarket(new Profile(firstName, lastName, dob),withdrawAmt,WITHDRAW_INDICATION);
        }
    }

    @FXML
    void printSorted(ActionEvent event) {
        accountDatabase.printSorted(textArea);

    }

    @FXML
    void loadAccountsFromFile(ActionEvent event) {

    }

    @FXML
    void printUpdatedBalances(ActionEvent event) {
        accountDatabase.printUpdatedBalances(textArea);
    }

    @FXML
    void printFeesAndInterests(ActionEvent event) {
        accountDatabase.printFeesAndInterests(textArea);
    }

}