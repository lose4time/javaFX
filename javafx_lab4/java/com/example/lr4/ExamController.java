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

public class ExamController implements Initializable{
	@FXML TableView<Exam_mark> exam_markTable;
	@FXML TableColumn<Exam_mark,String> studCol;
	@FXML TableColumn<Exam_mark,String> subCol;
	@FXML TableColumn<Exam_mark,Integer> markCol;
	@FXML TableColumn<Exam_mark,String> dateCol;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ArrayList<Exam_mark> exams=new ArrayList<Exam_mark>();
		Connection conn=null;
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		studCol.setCellValueFactory(new PropertyValueFactory<Exam_mark, String>("student"));
		subCol.setCellValueFactory(new PropertyValueFactory<Exam_mark, String>("subj"));
		markCol.setCellValueFactory(new PropertyValueFactory<Exam_mark, Integer>("mark"));
		dateCol.setCellValueFactory(new PropertyValueFactory<Exam_mark, String>("date"));


		studCol.prefWidthProperty().bind(exam_markTable.widthProperty().divide(4));
		subCol.prefWidthProperty().bind(exam_markTable.widthProperty().divide(4));
		markCol.prefWidthProperty().bind(exam_markTable.widthProperty().divide(4));
		dateCol.prefWidthProperty().bind(exam_markTable.widthProperty().divide(4));
		
		try {
			Class.forName("org.h2.Driver");
				try {
					conn=DriverManager.getConnection("jdbc:h2:file://D:\\NVSU\\3kurs\\java\\db\\university2","sa","");
					System.out.println(conn);
					stmt=conn.createStatement();
					stmt1=conn.createStatement();
					rs=stmt.executeQuery("select student.surname, subject.subj_name," +
							"exam_marks.mark, exam_marks.exam_date from student, subject," + 
							"exam_marks where student.student_id = exam_marks.student_id and subject.subj_id=exam_marks.subj_id");
					while (rs.next()) {
						exams.add(new Exam_mark(rs.getString("surname"),rs.getString("subj_name"), rs.getString("mark"),rs.getString("exam_date")));
					}
				} catch (SQLException ex) {
					Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
				}
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				if (conn!=null){
					try {
						conn.close();
					} catch (SQLException ex) {
						Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
			ObservableList<Exam_mark> data=FXCollections.observableArrayList(exams);
			exam_markTable.setItems(data);
		}
}
