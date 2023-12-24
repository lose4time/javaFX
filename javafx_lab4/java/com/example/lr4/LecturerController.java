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

public class LecturerController implements Initializable{
	@FXML TableView<Lecturer> lecturerTable;
	@FXML TableColumn<Lecturer, String> surnameCol;
	@FXML TableColumn<Lecturer, String> nameCol;
	@FXML TableColumn<Lecturer, String> cityCol;
	@FXML TableColumn<Lecturer, String> univerCol;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Lecturer> lecturers=new ArrayList<Lecturer>();
		Connection conn=null; //для соединения с БД
		Statement stmt=null; //для формирования выражений SQL
		Statement stmt1=null; //для формирования выражений SQL
		ResultSet rs=null; //для результатов выполнения команд SQL
		//связываем колонки таблицы с полями класса Student
		surnameCol.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("surname"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("name"));
		cityCol.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("city"));
		univerCol.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("univer"));
		//связываем предпочитаемую ширину колонок с шириной окна, задавая пропорции изменения колонок
		surnameCol.prefWidthProperty().bind(lecturerTable.widthProperty().divide(4));
		nameCol.prefWidthProperty().bind(lecturerTable.widthProperty().divide(4));
		cityCol.prefWidthProperty().bind(lecturerTable.widthProperty().divide(4));
		univerCol.prefWidthProperty().bind(lecturerTable.widthProperty().divide(4));
		try { //работа с БД ведется только внутри блока try ... catch
			Class.forName("org.h2.Driver"); //подгружаем драйвер для H2
				try { //еще один блок try ... catch
		// получаем доступ к БД jdbc:h2:file:university1 – находится в текущем каталоге проекта
					conn=DriverManager.getConnection("jdbc:h2:file://D:\\NVSU\\3kurs\\java\\db\\university2","sa","");
					System.out.println(conn);
		//получаем объект для выполнения команд SQL
					stmt=conn.createStatement();
		// еще один объект для выполнения команд SQL
					stmt1=conn.createStatement();
		//объекту rs присваиваем результат выполнения команды SQL
					rs=stmt.executeQuery("select lecturer.surname, lecturer.name, lecturer.city ,university.univ_name" +
							" from lecturer, university where lecturer.univ_id=university.univ_id");
		// цикл по всем записям из SQL-запроса
					while (rs.next()) {
		//записываем в массив students объекты класса Student с результатами запроса из БД
						lecturers.add(new Lecturer(rs.getString("surname"),rs.getString("name"), rs.getString("city") ,rs.getString("univ_name")));
					}
				} catch (SQLException ex) { //обрабатываем ошибки при работе с БД
					Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
				}
			} catch (ClassNotFoundException ex) { //обрабатываем исключение для загрузки H2
				Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
			} finally { //по окончании работы корректно закрываем соединение с БД
				if (conn!=null){
					try {
						conn.close();
					} catch (SQLException ex) {
						Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		//создаем список типа ObservableList, в который записываем все содержимое массива students
			ObservableList<Lecturer> data=FXCollections.observableArrayList(lecturers);
			lecturerTable.setItems(data);//загружаем все из data в таблицу на окне
		}
		
}

