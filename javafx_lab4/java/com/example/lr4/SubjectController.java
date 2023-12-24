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

public class SubjectController implements Initializable{
	@FXML TableView<Subject> subjectTable;
	@FXML TableColumn<Subject,String> nameCol;
	@FXML TableColumn<Subject,Integer> hoursCol;
	@FXML TableColumn<Subject,Integer> semCol;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ArrayList<Subject> subjects=new ArrayList<Subject>();
		Connection conn=null;
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		nameCol.setCellValueFactory(new PropertyValueFactory<Subject, String>("subj_name"));
		hoursCol.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("hour"));
		semCol.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("semester"));

		nameCol.prefWidthProperty().bind(subjectTable.widthProperty().divide(3));
		hoursCol.prefWidthProperty().bind(subjectTable.widthProperty().divide(3));
		semCol.prefWidthProperty().bind(subjectTable.widthProperty().divide(3));

		try {
			Class.forName("org.h2.Driver");
				try {
					conn=DriverManager.getConnection("jdbc:h2:file://D:\\NVSU\\3kurs\\java\\db\\university2","sa","");
					System.out.println(conn);
					stmt=conn.createStatement();
					stmt1=conn.createStatement();
					rs=stmt.executeQuery("select subject.subj_name, subject.hour, subject.semester" +
							" from subject");
					while (rs.next()) {
						subjects.add(new Subject(rs.getString("subj_name"),rs.getString("hour"),
								rs.getString("semester")));
					}
				} catch (SQLException ex) {
					Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
				}
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				if (conn!=null){
					try {
						conn.close();
					} catch (SQLException ex) {
						Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
			ObservableList<Subject> data=FXCollections.observableArrayList(subjects);
			subjectTable.setItems(data);
		}
}
