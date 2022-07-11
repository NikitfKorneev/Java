package com.example.progectkurs;

import java.sql.*;
public class VPN {
    public static int AIID;
    public static void mainRegist(String _Name,String _Name2,String _Name3, String _group,String _univer,String _login,String _password,String db,int id) {
        try {
            String Name_ = _Name;
            String Name2_ = _Name2;
            String Name3_ = _Name3;
            String login_ = _login;
            String password_ = _password;
            String group_ = _group;
            String univer_ = _univer;
            String tdb = db;
            String root_ = "student";
            int id_ = id;

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                    "std_2003_kurovoipgo", "std_2003_kurovoipgo");

            String query_id = "SELECT id FROM users;";
            ResultSet id_save;//выборка id

            String query_2 = "SELECT MAX(id) FROM users;";
            ResultSet count;
            ResultSet checkString;//выборка максимавльного id

            int count_int = 0;
            Statement Statement = connection.createStatement();
            Statement Statement_count = connection.createStatement();

            String States = "INSERT INTO " + config.USER_TABLE + "(" +
                    config.USER_SURNAME + "," + config.USER_FIRST_NAME
                    + "," + config.USER_SECOND_NAME + "," + config.USER_GROPE
                    + "," + config.USER_UNVIR + "," + config.USER_LOGIN_NAME + "," + config.USER_PASSWORD
                    + "," + config.USER_ROOT + ")" + " VALUES(?,?,?,?,?,?,?,?);";
            try {
                PreparedStatement human = connection.prepareStatement(States);
                human.setString(1, Name_);
                human.setString(2, Name2_);
                human.setString(3, Name3_);
                human.setString(4, group_);
                human.setString(5, univer_);
                human.setString(6, login_);
                human.setString(7, password_);
                human.setString(8, root_);
                human.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }}