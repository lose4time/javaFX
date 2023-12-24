package com.example.javafx_lab5;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class ThreadsGUIController {
    SimpleThread threadA = new SimpleThread("A", ("500"));
    SimpleThread threadB = new SimpleThread("B", ("500"));

    @FXML TextField textforA;
    @FXML TextField textforB;
    @FXML TextField timeforA;
    @FXML TextField timeforB;
    @FXML TextField textforthreads;
    @FXML TextField timeforthreads;
    @FXML FlowPane flowsPane;
    @FXML TextArea textArea1;
    @FXML TextArea textAreaForA;
    @FXML TextArea textAreaForB;

    @FXML
    private void handleOnStartA(ActionEvent e) {
        textAreaForA.setText("Start");
        threadA.setTextArea(textAreaForA);
        if (threadA.getState() == Thread.State.NEW) {
            threadA.start();
        }
    }

    @FXML
    private void handleOnStopA(ActionEvent e) {
        textAreaForA.setText("Stop");
        threadA.stopThread();
        threadA = new SimpleThread(textforA.getText() == "" ? "A" : textforA.getText(), timeforA.getText() == "" ? "500" : timeforA.getText());
    }

    @FXML
    private void handleOnStartB(ActionEvent e) {
        textAreaForB.setText("Start");
        threadB.setTextArea(textAreaForB);
        if (threadB.getState() == Thread.State.NEW) {
            threadB.start();
        }
    }

    @FXML
    private void handleOnStopB(ActionEvent e) {
        threadB.stopThread();
        threadB = new SimpleThread(textforB.getText() == "" ? "B" : textforB.getText(), timeforB.getText() == "" ? "500" : timeforB.getText());
        textAreaForB.setText("Stop");
    }

    @FXML
    private void handleChangeNameForA(ActionEvent e) {
        threadA.changeName((textforA.getText()));
    }

    @FXML
    private void handleChangeNameForB(ActionEvent e) {
        threadB.changeName((textforB.getText()));
    }

    @FXML
    private void handleChangeTimeForA(ActionEvent e) {
        threadA.changeTime((timeforA.getText()));
    }

    @FXML
    private void handleChangeTimeForB(ActionEvent e) {
        threadB.changeTime((timeforB.getText()));
    }

    @FXML
    private void handlecreateThreads(ActionEvent e) {
        SimpleThread thread = new SimpleThread(textforthreads.getText() == "" ? "Поток" : textforthreads.getText(),
                timeforthreads.getText() == "" ? "500" : timeforthreads.getText());
        Button startButton = new Button("Старт потока");
        Button endButton = new Button("Остановить поток");
        thread.setTextArea(textArea1);
        startButton.getStyleClass().add("startBut");
        endButton.getStyleClass().add("endBut");

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                if (thread.getState() == Thread.State.NEW) {
                    thread.start();
                }

            }
        });

        endButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                thread.stopThread();
                flowsPane.getChildren().remove(endButton);
                flowsPane.getChildren().remove(startButton);

            }
        });

        flowsPane.getChildren().add(startButton);
        flowsPane.getChildren().add(endButton);
        System.out.println(flowsPane.getChildren());
    }
}

