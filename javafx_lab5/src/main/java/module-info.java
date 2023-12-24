module com.example.javafx_lab5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_lab5 to javafx.fxml;
    exports com.example.javafx_lab5;
}