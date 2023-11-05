package com.example.banktransactionsgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TransactionManagerController {

    @FXML
    private TabPane mainTabPane;

    @FXML
    private Tab openClose, depositWithdraw, accountDatabase;

    @FXML
    private TextArea textArea;

    @FXML
    private ToggleGroup AccountType1, AccountType2, Campus;

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

    }

    @FXML
    void enableCampus(ActionEvent event) {

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

    }

    @FXML
    void loadAccountsFromFile(ActionEvent event) {

    }

    @FXML
    void printUpdatedBalances(ActionEvent event) {

    }

    @FXML
    void printFeesAndInterests(ActionEvent event) {

    }
}