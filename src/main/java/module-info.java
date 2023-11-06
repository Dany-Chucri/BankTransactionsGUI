module banktransactionsgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens banktransactionsgui1 to javafx.fxml;
    exports banktransactionsgui1;
}