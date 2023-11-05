package banktransactionsgui1;

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
    private void createSavings(Profile addProfile,double balance, boolean loyal,int operation){
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
    private Date createDate(String date,int collegeIndication){
        Date addDate = new Date(date);
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
    void open(ActionEvent event) {
        String firstName = firstName1.getText();
        String lastName = lastName1.getText();
        String dob = dob1.getEditor().getText();
        String initialDepositText = initialDeposit.getText();
        double initialDeposit = Double.parseDouble(initialDepositText);
        int indication =0;
        if(collegeChecking1.isSelected()) {indication=2;}
        Date dateInput = createDate(dob, indication);
        if (dateInput == null){return;}
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
            banktransactionsgui1.Campus campus;
            if (campusText.equals("NB")) {
                campus = banktransactionsgui1.Campus.NEW_BRUNSWICK;
            } else if (campusText.equals("Newark")) {
                campus = banktransactionsgui1.Campus.NEWARK;
            } else if (campusText.equals("Camden")) {
                campus = banktransactionsgui1.Campus.CAMDEN;
            } else {
                campus = null;
            }
            createCollegeChecking(new Profile(firstName, lastName, dateInput),initialDeposit,campus,OPEN_INDICATION);
        } else if (moneyMarket1.isSelected()) {
            createMoneyMarket(new Profile(firstName, lastName, dateInput),initialDeposit,OPEN_INDICATION);
        }
    }

    @FXML
    void close(ActionEvent event) {
        String firstName = firstName1.getText();
        String lastName = lastName1.getText();
        Date dob = new Date(dob1.getEditor().getText());
        if (dob.isValid() == 2) {
            textArea.appendText("DOB invalid: " + dob + " cannot be today or a future day.\n");
            return;
        }
        if (checking1.isSelected()) {
            createChecking(new Profile(firstName, lastName, dob), 0, CLOSE_INDICATION);
        } else if (savings1.isSelected()) {
            createSavings(new Profile(firstName, lastName, dob),0,false,CLOSE_INDICATION);
        } else if (collegeChecking1.isSelected()) {
            createCollegeChecking(new Profile(firstName, lastName, dob),0, banktransactionsgui1.Campus.NEW_BRUNSWICK,CLOSE_INDICATION);
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
        if (dob.isValid() == 2) {
            textArea.appendText("DOB invalid: " + dob + " cannot be today or a future day.\n");
            return;
        }
        depositAmount = checkBalance(depositAmountTxt, "Deposit");
        if (depositAmount <= 0) return;

        if (checking2.isSelected()) {
            createChecking(new Profile(firstName, lastName, dob), depositAmount, DEPOSIT_INDICATION);
        } else if (savings2.isSelected()) {
            createSavings(new Profile(firstName, lastName, dob),depositAmount,false,DEPOSIT_INDICATION);
        } else if (collegeChecking2.isSelected()) {
            createCollegeChecking(new Profile(firstName, lastName, dob),depositAmount, banktransactionsgui1.Campus.NEW_BRUNSWICK,DEPOSIT_INDICATION);
        } else if (moneyMarket2.isSelected()) {
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
        if (dob.isValid() == 2) {
            textArea.appendText("DOB invalid: " + dob + " cannot be today or a future day.\n");
            return;
        }
        withdrawAmt = checkBalance(withdrawAmtTxt, "Withdraw");
        if (withdrawAmt <= 0) return;
        if (checking2.isSelected()) {
            createChecking(new Profile(firstName, lastName, dob), withdrawAmt, WITHDRAW_INDICATION);
        } else if (savings2.isSelected()) {
            createSavings(new Profile(firstName, lastName, dob),withdrawAmt,false,WITHDRAW_INDICATION);
        } else if (collegeChecking2.isSelected()) {
            createCollegeChecking(new Profile(firstName, lastName, dob),withdrawAmt, banktransactionsgui1.Campus.NEW_BRUNSWICK,WITHDRAW_INDICATION);
        } else if (moneyMarket2.isSelected()) {
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