module com.example.banktransactionsgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.banktransactionsgui to javafx.fxml;
    exports com.example.banktransactionsgui;
}