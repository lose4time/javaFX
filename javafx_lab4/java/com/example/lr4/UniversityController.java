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

public class UniversityController implements Initializable{
	@FXML TableView<University> universityTable;
	@FXML TableColumn<University,String> nameCol;
	@FXML TableColumn<University,Integer> ratingCol;
	@FXML TableColumn<University,String> cityCol;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ArrayList<University> university=new ArrayList<University>();
		Connection conn=null;
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		nameCol.setCellValueFactory(new PropertyValueFactory<University, String>("univ_name"));
		ratingCol.setCellValueFactory(new PropertyValueFactory<University, Integer>("rating"));
		cityCol.setCellValueFactory(new PropertyValueFactory<University, String>("city"));


		nameCol.prefWidthProperty().bind(universityTable.widthProperty().divide(3));
		ratingCol.prefWidthProperty().bind(universityTable.widthProperty().divide(3));
		cityCol.prefWidthProperty().bind(universityTable.widthProperty().divide(3));

		try {
			Class.forName("org.h2.Driver");
				try {
					conn=DriverManager.getConnection("jdbc:h2:file://D:\\NVSU\\3kurs\\java\\db\\university2","sa","");
					System.out.println(conn);
					stmt=conn.createStatement();
					stmt1=conn.createStatement();
					rs=stmt.executeQuery("select university.univ_name, university.rating, university.city" +
							" from university");
					while (rs.next()) {
						university.add(new University(rs.getString("univ_name"),rs.getString("rating"),
								rs.getString("city")));
					}
				} catch (SQLException ex) {
					Logger.getLogger(UniversityController.class.getName()).log(Level.SEVERE, null, ex);
				}
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(UniversityController.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				if (conn!=null){
					try {
						conn.close();
					} catch (SQLException ex) {
						Logger.getLogger(UniversityController.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
			ObservableList<University> data=FXCollections.observableArrayList(university);
			universityTable.setItems(data);
		}
}
