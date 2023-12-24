package com.example.lr4;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLdbWindowController {
    @FXML
    private BorderPane mainPane;

    @FXML
    private void handleShowStudents(ActionEvent event) throws IOException{

        System.out.println("StudentsTable!!");
        BorderPane dbPane=new BorderPane(); //базовый контейнер в подгружаемом интерфейсе
        dbPane=(BorderPane)FXMLLoader.load(getClass().getResource("Students.fxml"));
        System.out.println(dbPane);
        TableView myTable=(TableView) dbPane.getCenter();
        mainPane.setCenter(myTable);
    }

    @FXML
    private void handleShowLecturer(ActionEvent event) throws IOException{
        System.out.println("LecturerTable!!");
        Parent root = FXMLLoader.load(getClass().getResource("Lecturer.fxml"));
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainPane.getScene().getWindow());
        stage.show();
    }

    @FXML
    private void handleShowUniversity(ActionEvent event) throws IOException{
        System.out.println("University!!");
        Parent root = FXMLLoader.load(getClass().getResource("University.fxml"));
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainPane.getScene().getWindow());
        stage.show();
    }

    @FXML
    private void handleShowSubject(ActionEvent event) throws IOException{
        System.out.println("Subject!!");
        Parent root = FXMLLoader.load(getClass().getResource("subject.fxml"));
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainPane.getScene().getWindow());
        stage.show();
    }

    @FXML
    private void handleShowSubj_lect(ActionEvent event) throws IOException{
        System.out.println("Subject_Lect!!");
        Parent root = FXMLLoader.load(getClass().getResource("subj_lect.fxml"));
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainPane.getScene().getWindow());
        stage.show();
    }

    @FXML
    private void handleShowExam_marks(ActionEvent event) throws IOException{
        System.out.println("Exam_marks!!");
        Parent root = FXMLLoader.load(getClass().getResource("exam_mark.fxml"));
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainPane.getScene().getWindow());
        stage.show();
    }
}
