package com.example.banktransactionsgui;

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
    private Button open, close, clear, deposit, withdraw, printAll, loadAccounts, printFeesInterest, updatePrint;

    @FXML
    private RadioButton checking1, checking2, savings1, savings2, collegeChecking1, collegeChecking2,
        moneyMarket1, moneyMarket2, camden, nb, newark;

    @FXML
    private CheckBox loyalty;

}