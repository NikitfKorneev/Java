package com.example.progectkurs;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class admin {
    private ObservableList<User1> usersData = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tegChangeName1;

    @FXML
    private TextField tegChangeName2;

    @FXML
    private TextField tegChangeName3;

    @FXML
    private TextField tegChengeState;

    @FXML
    private TextField tegChengeSybj;

    @FXML
    private TextField tegIDStatets;

    @FXML
    private Button tegGoEdit;

    @FXML
    private TextField tegChenge2Name;

    @FXML
    private TextField tegChenge3Name;

    @FXML
    private TextField tegChengeGroupe;

    @FXML
    private TextField tegChengeName;

    @FXML
    private TextField tegChengePassword;

    @FXML
    private TextField tegChengeUnvirsitet;

    @FXML
    private TextField tegChengelOGIN;

    @FXML
    private Button tegExit;

    @FXML
    private Button tegGoSitata;

    @FXML
    private TextField tegName;

    @FXML
    private Label tegLabID;

    @FXML
    private Label tegLabID1;

    @FXML
    private Button tegCount1;

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
    private TextField tegidChenge;

    @FXML
    private TextField tegLastName;

    @FXML
    private TableView<User1> tegTextTable;

    @FXML
    private Button tegEdit;

    @FXML
    public void initialize() {
        tegExit.setOnAction(actionEvent -> {
            tegExit.getScene().getWindow().hide();

            Main.mainStage.show();//Хорошо работает
        });
        DBUP();


        tegGoSitata.setOnAction(actionEvent -> {
            tegGoSitata.getScene().getWindow().hide();

            Main.mainStage.show();//Хорошо работает
            TableDB();
        });

        tegEdit.setOnAction(actionEvent -> {
            tegEdit.getScene().getWindow().hide();

            Main.mainStage.show();//Хорошо работает
            UpdateUsers();
        });
        tegGoEdit.setOnAction(actionEvent -> {
            tegGoEdit.getScene().getWindow().hide();

            Main.mainStage.show();//Хорошо работает
            UpdeteStates();
        });
        CountStates();
        tegCount1.setOnAction(actionEvent -> {
            CountStates1();
        });
    }

    private void CountStates() {
        tegLabID1.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                    "std_2003_kurovoipgo", "std_2003_kurovoipgo");

            ResultSet Info;
            Statement Statement = connection.createStatement();
            Info= Statement.executeQuery("Select Count(*) from states\n" +
                    "Where id_login = '" + DataLogin.ID + "'");
            while (Info.next())
                tegLabID1.setText(Info.getString(1));
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void CountStates1() {
        tegLabID.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                    "std_2003_kurovoipgo", "std_2003_kurovoipgo");
            ResultSet Info;
            Statement Statement = connection.createStatement();
            Info = Statement.executeQuery("Select Count(*) from states\n" +
                    "Where id_login = '" + DataLogin.ID + "'");
            while (Info.next())
                tegLabID.setText(Info.getString(1));
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void UpdeteStates() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                    "std_2003_kurovoipgo", "std_2003_kurovoipgo");
            Statement statement = connection.createStatement();
            try {
                int count = statement.executeUpdate("update states\n" +
                        "set\n" +
                        "pname = '" + tegChangeName1.getText() + "',\n" +
                        "secondname = '" + tegChangeName2.getText() + "',\n" +
                        "lastname = '" + tegChangeName3.getText() + "',\n" +
                        "subject = '" + tegChengeSybj.getText() + "',\n" +
                        "states = '" + tegChengeState.getText() + "'\n" +
                        "where id = '" + tegIDStatets.getText() + "'");
                System.out.println("Строк изменено - " + count);
                tegTextTable.getItems().clear();
                DBUP();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void UpdateUsers() {
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                        "std_2003_kurovoipgo", "std_2003_kurovoipgo");

                Statement statement = connection.createStatement();

                try {
                    String newPassword = null;
                    try {
                        MessageDigest md5 = MessageDigest.getInstance("MD5");
                        byte[] bytes = md5.digest(tegChengePassword.getText().getBytes());
                        StringBuilder sb = new StringBuilder();
                        StringBuilder builder = new StringBuilder();
                        for (byte password_hash : bytes) {
                            builder.append(String.format("%02X", password_hash));
                        }
                        newPassword = builder.toString();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }

                    int count = statement.executeUpdate("update users\n" +
                            "set\n" +
                            "password = '" + newPassword + "',\n" +
                            "groups = '" + tegChengeGroupe.getText() + "',\n" +
                            "unversity = '" + tegChengeUnvirsitet.getText() + "',\n" +
                            "name = '" + tegChengeName.getText() + "',\n" +
                            "secondname = '" + tegChenge2Name.getText() + "',\n" +
                            "lastname = '" + tegChenge3Name.getText() + "',\n" +
                            "login = '" + tegChengelOGIN.getText() + "'\n" +
                            "WHERE id = '" + tegidChenge.getText() + "'");
                    System.out.println("Строк изменено - " + count);
                    tegTextTable.getItems().clear();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    private void TableDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                    "std_2003_kurovoipgo", "std_2003_kurovoipgo");
            ResultSet _id;
            PreparedStatement register ;
            Statement statement = connection.createStatement();
            ResultSet count;
            ResultSet Saveid;
            int PeopleID = 0;
            int idRegPeople = 0;
            int check = 0;
            try {
                count = statement.executeQuery("SELECT COUNT(*) FROM states");
                while (count.next())
                    check = count.getInt(1);
                if (check != 0) {
                    Saveid = statement.executeQuery("SELECT MAX(id) FROM states");
                    while (Saveid.next()) {
                        idRegPeople = Saveid.getInt(1) + 1;
                    }
                } else idRegPeople = 1;
                String saveLogin = DataLogin.login;
                _id = statement.executeQuery("SELECT id FROM users WHERE login = '" + saveLogin + "'");
                while (_id.next()) {
                    PeopleID = _id.getInt(1);
                }
                register = connection.prepareStatement("INSERT INTO " + config.STATE_TABLE + "(" + config.STATE_ID + "," + config.STATE_STATES
                        + "," + config.STATE_SURNAME + "," + config.STATE_DATA + "," +
                        config.STATE_FIRST + "," + config.STATE_SECOND + "," + config.STATE_ID_LOGIN + "," +
                        config.STATE_SUBJECT + ")" + " VALUES(?,?,?,?,?,?,?,?)");

                Date d = new Date();

                register.setInt(1, idRegPeople);
                register.setString(2, tegState.getText());
                register.setString(3, tegName.getText());
                register.setString(4, d.toString());
                register.setString(5, tegsecondName.getText());
                register.setString(6, tegLastName.getText());
                register.setInt(7, PeopleID);
                register.setString(8, tegsubject.getText());
                register.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void SetStates() {

        tegTextState.setCellValueFactory(new PropertyValueFactory<admin.User1, String>("states"));
        tegTextName.setCellValueFactory(new PropertyValueFactory<admin.User1, String>("pname"));
        tegTextSName.setCellValueFactory(new PropertyValueFactory<admin.User1, String>("secondname"));
        tegTextSubject.setCellValueFactory(new PropertyValueFactory<admin.User1, String>("subject"));
        tegTextDate.setCellValueFactory(new PropertyValueFactory<admin.User1, String>("date"));
        tegTextTable.setItems(usersData);
    }

    public void DBUP() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                    "std_2003_kurovoipgo", "std_2003_kurovoipgo");
            ResultSet Info;
            Statement Statement = connection.createStatement();
            try {
                Info = Statement.executeQuery("SELECT * FROM states");
                while (Info.next()) {
                    usersData.add(new admin.User1(Info.getString(2), Info.getString(3),
                            Info.getString(5), Info.getString(8),
                            Info.getString(4)));
                    SetStates();
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

        public User1(String states, String pname, String secondname, String subject, String date) {

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

        public String getSubject() {
            return subject;
        }

        public String getSecondname() {
            return secondname;
        }

    }
}