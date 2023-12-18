module com.example.javafx_lab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_lab2 to javafx.fxml;
    exports com.example.javafx_lab2;
}