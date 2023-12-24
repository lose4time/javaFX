module com.example.lr4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;


    opens com.example.lr4 to javafx.fxml;
    exports com.example.lr4;
}