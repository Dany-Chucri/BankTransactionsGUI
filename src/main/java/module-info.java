module com.example.banktransactionsgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens banktransactionsgui1 to javafx.fxml;
    exports banktransactionsgui1;
}