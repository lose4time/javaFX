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

public class SubjLectController implements Initializable{
	@FXML TableView<SubjLect> subj_lectTable;
	@FXML TableColumn<SubjLect,String> lectorCol;
	@FXML TableColumn<SubjLect,String> subjCol;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ArrayList<SubjLect> sublects=new ArrayList<SubjLect>();
		Connection conn=null;
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		lectorCol.setCellValueFactory(new PropertyValueFactory<SubjLect, String>("lector"));
		subjCol.setCellValueFactory(new PropertyValueFactory<SubjLect, String>("subj"));


		lectorCol.prefWidthProperty().bind(subj_lectTable.widthProperty().divide(2));
		subjCol.prefWidthProperty().bind(subj_lectTable.widthProperty().divide(2));
		try {
			Class.forName("org.h2.Driver");
				try {
					conn=DriverManager.getConnection("jdbc:h2:file://D:\\NVSU\\3kurs\\java\\db\\university2","sa","");
					System.out.println(conn);
					stmt=conn.createStatement();
					stmt1=conn.createStatement();
					rs=stmt.executeQuery("select lecturer.name, subject.subj_name" +
							" from lecturer, subject, subj_lect where subj_lect.lecturer_id = lecturer.lecturer_id and subject.subj_id=subj_lect.subj_id");
					while (rs.next()) {
						sublects.add(new SubjLect(rs.getString("name"),rs.getString("subj_name")));
					}
				} catch (SQLException ex) {
					Logger.getLogger(SubjLectController.class.getName()).log(Level.SEVERE, null, ex);
				}
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(SubjLectController.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				if (conn!=null){
					try {
						conn.close();
					} catch (SQLException ex) {
						Logger.getLogger(SubjLectController.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
			ObservableList<SubjLect> data=FXCollections.observableArrayList(sublects);
			subj_lectTable.setItems(data);
		}
}
