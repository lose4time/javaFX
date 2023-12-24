package com.example.lr4;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentsController implements Initializable{
    @FXML TableView<Student> studentsTable;
    @FXML TableColumn<Student,String> surnameCol;
    @FXML TableColumn<Student,String> nameCol;
    @FXML TableColumn<Student,Integer> stipendCol;
    @FXML TableColumn<Student,Integer> kursCol;
    @FXML TableColumn<Student,String> cityCol;
    @FXML TableColumn<Student,String> birthdayCol;
    @FXML TableColumn<Student,String> univerCol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Student> students=new ArrayList<Student>();
        Connection conn=null;
        Statement stmt=null;
        Statement stmt1=null;
        ResultSet rs=null;
        surnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        stipendCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("stipend"));
        kursCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("kurs"));
        cityCol.setCellValueFactory(new PropertyValueFactory<Student, String>("city"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<Student, String>("birthday"));
        univerCol.setCellValueFactory(new PropertyValueFactory<Student, String>("univer"));

        surnameCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(7));
        nameCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(7));
        stipendCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(7));
        kursCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(7));
        cityCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(7));
        birthdayCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(7));
        univerCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(7));
        try {
            Class.forName("org.h2.Driver");
            try {
                conn=DriverManager.getConnection("jdbc:h2:file://D:\\NVSU\\3kurs\\java\\db\\university2","sa","");
                System.out.println(conn);
                stmt=conn.createStatement();
                stmt1=conn.createStatement();
                rs=stmt.executeQuery("select student.surname, student.name, student.stipend," +
                        "student.kurs, student.city, student.birthday,university.univ_name" +
                        " from student, university where student.univ_id=university.univ_id");
                System.out.println(rs);
                while (rs.next()) {
                    students.add(new Student(rs.getString("surname"),rs.getString("name"),
                            rs.getString("stipend"), rs.getString("kurs"), rs.getString("city"),
                            rs.getString("birthday"),rs.getString("univ_name")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        ObservableList<Student> data=FXCollections.observableArrayList(students);
        studentsTable.setItems(data);
    }
}
