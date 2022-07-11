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
        GetQuoteFromDataBase();


        tegGoSitata.setOnAction(actionEvent -> {
            tegGoSitata.getScene().getWindow().hide();

            Main.mainStage.show();//Хорошо работает
            AddInfoToDataBase();
        });

        tegEdit.setOnAction(actionEvent -> {
            tegEdit.getScene().getWindow().hide();

            Main.mainStage.show();//Хорошо работает
            EditInfoToDataBase();
        });
        tegGoEdit.setOnAction(actionEvent -> {
            tegGoEdit.getScene().getWindow().hide();

            Main.mainStage.show();//Хорошо работает
            EditStateToDataBase();
        });
        Count();
        tegCount1.setOnAction(actionEvent -> {
            CountDB();
        });
    }

    private void Count() {
        tegLabID1.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                    "std_2003_kurovoipgo", "std_2003_kurovoipgo");

            ResultSet GetInfo;
            Statement statement = connection.createStatement();
            GetInfo = statement.executeQuery("Select Count(*) from states\n" +
                    "Where id_login = '" + DataLogin.ID + "'");
            while (GetInfo.next())
                tegLabID1.setText(GetInfo.getString(1));

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void CountDB() {
        tegLabID.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                    "std_2003_kurovoipgo", "std_2003_kurovoipgo");


            ResultSet GetInfo;
            Statement statement = connection.createStatement();
            GetInfo = statement.executeQuery("Select Count(*) from states\n" +
                    "Where id_login = '" + DataLogin.ID + "'");
            while (GetInfo.next())
                tegLabID.setText(GetInfo.getString(1));

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void EditStateToDataBase() {
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
                System.out.println("Строк изменено " + count);
                tegTextTable.getItems().clear();
                GetQuoteFromDataBase();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private void EditInfoToDataBase() {
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
                    System.out.println("Строк изменено " + count);
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
    public void SetQuoteTo() {

        tegTextState.setCellValueFactory(new PropertyValueFactory<admin.User1, String>("states"));
        tegTextName.setCellValueFactory(new PropertyValueFactory<admin.User1, String>("pname"));
        tegTextSName.setCellValueFactory(new PropertyValueFactory<admin.User1, String>("secondname"));
        tegTextSubject.setCellValueFactory(new PropertyValueFactory<admin.User1, String>("subject"));
        tegTextDate.setCellValueFactory(new PropertyValueFactory<admin.User1, String>("date"));
        tegTextTable.setItems(usersData);
    }


    public void GetQuoteFromDataBase() {
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
                    usersData.add(new admin.User1(GetInfo.getString(2), GetInfo.getString(3),
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