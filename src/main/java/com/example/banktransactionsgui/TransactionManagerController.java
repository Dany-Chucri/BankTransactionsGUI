package com.example.banktransactionsgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
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
        String firstName = firstName1.getText();
        String lastName = lastName1.getText();
        Date dob = new Date(dob1.getEditor().getText());
        String initialDepositText = initialDeposit.getText();
        double initialDeposit = Double.parseDouble(initialDepositText);

        Account newAccount = null;
        if (checking1.isSelected()) {
            newAccount = new Checking(new Profile(firstName, lastName, dob), initialDeposit);
        } else if (savings1.isSelected()) {
            newAccount = new Savings(new Profile(firstName, lastName, dob), initialDeposit);
        } else if (collegeChecking1.isSelected()) {
            Campus campus = getSelectedCampus(); // Implement a method to determine campus
            newAccount = new CollegeChecking(new Profile(firstName, lastName, dob, campus), initialDeposit);
        } else if (moneyMarket1.isSelected()) {
            newAccount = new MoneyMarket(new Profile(firstName, lastName, dob), initialDeposit);
        }
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