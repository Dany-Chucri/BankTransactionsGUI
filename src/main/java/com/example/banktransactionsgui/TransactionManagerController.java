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
    private static final int  INVALID_DATE= 1;
    private static final int  NO_TODAY_NO_FUTURE= 2;

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
    void open(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void deposit(ActionEvent event) {

    }

    @FXML
    void withdraw(ActionEvent event) {

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