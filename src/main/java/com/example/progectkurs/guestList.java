package com.example.progectkurs;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class guestList {
    private ObservableList<User1> usersData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button tegBack;

    @FXML
    private TableColumn<User1, String> tegDate;

    @FXML
    private TableColumn<User1, String> tegId;

    @FXML
    private TableColumn<User1, String> tegName;

    @FXML
    private TableColumn<User1, String> tegSname;

    @FXML
    private TableColumn<User1, String> tegState;

    @FXML
    private TableColumn<User1, String> tegSubject;

    @FXML
    private TableView<User1> tegTable;


        @FXML
        public void initialize() {
            tegBack.setOnAction(actionEvent -> {
                tegBack.getScene().getWindow().hide();

          Main.mainStage.show();//Хорошо работает
            });
            DBUP();
        }

        public void SetStates(){
            tegId.setCellValueFactory(new PropertyValueFactory<User1,String>("id"));
            tegState.setCellValueFactory(new PropertyValueFactory<User1,String>("states"));
            tegName.setCellValueFactory(new PropertyValueFactory<User1,String>("pname"));
            tegSname.setCellValueFactory(new PropertyValueFactory<User1,String>("secondname"));
            tegSubject.setCellValueFactory(new PropertyValueFactory<User1,String>("subject"));
            tegSubject.setCellValueFactory(new PropertyValueFactory<User1,String>("id_login"));
            tegDate.setCellValueFactory(new PropertyValueFactory<User1,String>("date"));
            tegTable.setItems(usersData);
        }


        public void DBUP()
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                        "std_2003_kurovoipgo", "std_2003_kurovoipgo");

                ResultSet Info;
                Statement statement = connection.createStatement();

                try{
                    Info = statement.executeQuery("SELECT * FROM states");
                    while (Info.next()){
                        usersData.add(new User1(Info.getString(1),Info.getString(2),
                                Info.getString(3),Info.getString(4),
                                Info.getString(5), Info.getString(6),
                                Info.getString(7),Info.getString(8)));
                        SetStates();
                    }

                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public class User1 {

            private String states;
            private String pname;
            private String date;
            private String secondname;
            private String lastname;
            private String subject;
            private String id_login;
            private String id;

            public User1(String id, String states, String pname, String date, String secondname, String lastname, String subject, String id_login) {
                this.pname = pname;
                this.date = date;
                this.id = id;
                this.lastname = lastname;
                this.states = states;
                this.secondname = secondname;
                this.subject = subject;
                this.id_login = id_login;
            }

            public void setpname(String pname) {
                this.pname = pname;
            }

            public void setId_login(String id_login) {
                this.id_login = id_login;
            }

            public void setsecondname(String secondname) {
                this.secondname = secondname;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void states(String states) {
                this.states = states;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public void setSurname(String lastname) {
                this.lastname = lastname;
            }

            public User1() {
            }

            public String getPname() {
                return pname;
            }

            public String getDate() {
                return date;
            }

            public String getId() {
                return id;
            }

            public String getSurname() {
                return lastname;
            }

            public String getStates() {
                return states;
            }
            public String getId_login(){
                return  id_login;
        }
            public String getSubject(){
                return  subject;
            }
            public String getSecondname(){
                return  secondname;

        }

    }
}

