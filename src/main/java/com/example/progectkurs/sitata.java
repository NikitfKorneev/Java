package com.example.progectkurs;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class sitata {
    private ObservableList<User1> usersData = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button tegExit;

    @FXML
    private Button tegGoSitata;

    @FXML
    private TextField tegName;

    @FXML
    private TextField tegState;

    @FXML
    private TableColumn<User1, String> tegTextDate;

    @FXML
    private TableColumn<User1, String> tegTextName;

    @FXML
    private TableColumn<User1, String> tegTextSName;

    @FXML
    private TableColumn<User1, String> tegTextState;

    @FXML
    private TableColumn<User1, String> tegTextSubject;

    @FXML
    private TextField tegsecondName;

    @FXML
    private TextField tegsubject;

    @FXML
    private TextField tegLastName;

    @FXML
    private TableView<User1> tegTextTable;

    @FXML
    public void initialize() {
        tegExit.setOnAction(actionEvent -> {
            tegExit.getScene().getWindow().hide();

            Main.mainStage.show();//Хорошо работает
        });
        GetQuoteFromDataBase();


        tegGoSitata.setOnAction(actionEvent -> {
            tegGoSitata.getScene().getWindow().hide();

            Main.mainStage.show();//Хорошо работает
            AddInfoToDataBase();
        });


    }
    private void AddInfoToDataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                    "std_2003_kurovoipgo", "std_2003_kurovoipgo");


            ResultSet GetID;
            PreparedStatement reg = null;
            Statement statement = connection.createStatement();
            ResultSet count;
            ResultSet id_save;
            int PeopleID = 0;
            int id_registr_people = 0;
            int check = 0;
            try {
                count = statement.executeQuery("SELECT COUNT(*) FROM states");
                while (count.next())
                    check = count.getInt(1);
                if (check != 0) {
                    id_save = statement.executeQuery("SELECT MAX(id) FROM states");
                    while (id_save.next()) {
                        id_registr_people = id_save.getInt(1) + 1;
                    }
                } else id_registr_people = 1;
                String savelogin = DataLogin.login;
                GetID = statement.executeQuery("SELECT id FROM users WHERE login = '" + savelogin + "'");
                while (GetID.next()) {
                    PeopleID = GetID.getInt(1);
                }

                reg = connection.prepareStatement("INSERT INTO " + config.STATE_TABLE + "(" + config.STATE_ID + "," + config.STATE_STATES
                        + "," + config.STATE_SURNAME + "," + config.STATE_DATA + "," +
                        config.STATE_FIRST + "," + config.STATE_SECOND + "," + config.STATE_ID_LOGIN + "," +
                        config.STATE_SUBJECT + ")" + " VALUES(?,?,?,?,?,?,?,?)");

                Date d = new Date();

                reg.setInt(1, id_registr_people);
                reg.setString(2, tegState.getText());
                reg.setString(3, tegName.getText());
                reg.setString(4, d.toString());
                reg.setString(5, tegsecondName.getText());
                reg.setString(6, tegLastName.getText());
                reg.setInt(7, PeopleID);
                reg.setString(8, tegsubject.getText());
                reg.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @FXML
        public void SetQuoteTo(){

        tegTextState.setCellValueFactory(new PropertyValueFactory<sitata.User1,String>("states"));
        tegTextName.setCellValueFactory(new PropertyValueFactory<sitata.User1,String>("pname"));
        tegTextSName.setCellValueFactory(new PropertyValueFactory<sitata.User1,String>("secondname"));
        tegTextSubject.setCellValueFactory(new PropertyValueFactory<sitata.User1,String>("subject"));
        tegTextDate.setCellValueFactory(new PropertyValueFactory<sitata.User1,String>("date"));
        tegTextTable.setItems(usersData);
        }


        public void GetQuoteFromDataBase()
            {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                            "std_2003_kurovoipgo", "std_2003_kurovoipgo");

                    ResultSet GetInfo;
                    Statement statement = connection.createStatement();

                    try {
                        GetInfo = statement.executeQuery("SELECT * FROM states");
                        while (GetInfo.next()) {
                            usersData.add(new sitata.User1(GetInfo.getString(2), GetInfo.getString(3),
                                    GetInfo.getString(5), GetInfo.getString(8),
                                    GetInfo.getString(4)));
                            SetQuoteTo();
                        }

                    } catch (SQLException e) {
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
            private String secondname;
            private String subject;
            private String date;

            public User1( String states, String pname, String secondname,  String subject,String date) {
                this.pname = pname;
                this.date = date;
                this.states = states;
                this.secondname = secondname;
                this.subject = subject;
            }

            public void setpname(String pname) {
                this.pname = pname;
            }

            public void setsecondname(String secondname) {
                this.secondname = secondname;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void states(String states) {
                this.states = states;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public User1() {
            }

            public String getPname() {
                return pname;
            }

            public String getDate() {
                return date;
            }

            public String getStates() {
                return states;
            }

            public String getSubject(){
                return  subject;
            }
            public String getSecondname(){
                return  secondname;
            }

    }
}