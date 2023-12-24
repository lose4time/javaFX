module com.example.javafx_lab3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_lab3 to javafx.fxml;
    exports com.example.javafx_lab3;
}