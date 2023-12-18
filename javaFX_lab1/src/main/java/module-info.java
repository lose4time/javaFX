module com.example.javafx_lab1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_lab1 to javafx.fxml;
    exports com.example.javafx_lab1;
}