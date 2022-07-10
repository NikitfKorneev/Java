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
            Statement statement = connection.createStatement();
            Statement statement_count = connection.createStatement();
            Statement test = connection.createStatement();

            String query = "INSERT INTO " + config.USER_TABLE + "(" +
                    config.USER_SURNAME + "," + config.USER_FIRST_NAME
                    + "," + config.USER_SECOND_NAME + "," + config.USER_GROPE
                    + "," + config.USER_UNVIR + "," + config.USER_LOGIN_NAME + "," + config.USER_PASSWORD
                    + "," + config.USER_ROOT + ")" + " VALUES(?,?,?,?,?,?,?,?);";
            try {
                PreparedStatement human = connection.prepareStatement(query);
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

//            try {
//                int check = 0;
//                int csharpluchse = 0;
//                PreparedStatement reg = null;
//                count = statement.executeQuery("SELECT COUNT(*) FROM users;");
//                while (count.next())
//                    check = count.getInt(1);
//                if (check != 0) {
//                    checkString = statement.executeQuery("SELECT login FROM users;");
//                    while (checkString.next()) {
//                        if (_login.equals(checkString.getString(1))) {
//                            csharpluchse = 1;
//                            break;
//                        } else csharpluchse = 0;
//                    }
//                }

//                if (check != 0) {
//                    id_save = statement.executeQuery(query_2);
//
//                    while (id_save.next()) {
//                        id_ = id_save.getInt(1) + 1;
//                    }
//                } else id_ = 1;
//                if (csharpluchse == 0) {
//                    reg = connection.prepareStatement(query);
//                    //reg.setString(1, table_registr_people);
//                    reg.setInt(1, id_);
//                    reg.setString(2, Name_);
//                    reg.setString(3, Name2_);
//                    reg.setString(4, Name3_);
//                    reg.setString(5, group_);
//                    reg.setString(6, univer_);
//                    reg.setString(7, login_);
//                    reg.setString(8, password_);
//                    reg.setString(9, root_);
//                    reg.executeUpdate();
//                } else if (csharpluchse == 1) {
//                }

//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
                //ResultSet result = statement.executeQuery(query);
//                connection.close();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//    }
            } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }}