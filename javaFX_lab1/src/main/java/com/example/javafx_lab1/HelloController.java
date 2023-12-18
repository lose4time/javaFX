package com.example.javafx_lab1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;
    @FXML
    private ListView listView;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


        listView.getItems().add("четный");
        listView.getItems().add("нечетный");
        listView.getItems().add("четный");
        listView.getItems().add("нечетный");
        listView.getItems().add("четный");
        listView.getItems().add("нечетный");
        listView.getItems().add("четный");
        listView.getItems().add("нечетный");
        listView.getItems().add("четный");
        listView.getItems().add("нечетный");
        listView.getItems().add("четный");
        listView.getItems().add("нечетный");

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textArea.appendText("HelloWorld!");
            }
        });

    }
    @FXML
    private void handleMouseEnter (MouseEvent event) {
        textField.setText(event.getSource().toString());
    }
    @FXML
    private void handle(MouseEvent event) {
        textArea.appendText("Hellowwww");
    }
    @FXML
    private void handleRadioButton(ActionEvent event){
//получаем ссылку на объект, вызвавший событие
        RadioButton selRadio=(RadioButton)event.getSource();
//очищаем установленные стили (на сцене и на главной панели)
        selRadio.getScene().getStylesheets().clear();
        selRadio.getScene().getRoot().getStylesheets().clear();
//делаем switch по тексту на радиокнопке (в java 1.7 это стало возможно)
        switch (selRadio.getText()) {
            case "красный":
//берем родительскую сцену (основу окна в JavaFX) и добавляем в ее список стилей новый из файла
                selRadio.getScene().getStylesheets().add(getClass().getResource("application.css").
                        toExternalForm());
                break;
            case "синий":
                selRadio.getScene().getStylesheets().add(getClass().getResource("application2.css").
                        toExternalForm());
                break;

                case "зеленый":
                selRadio.getScene().getStylesheets().add(getClass().getResource("application3.css").
                        toExternalForm());
                break;
        }
    }
}