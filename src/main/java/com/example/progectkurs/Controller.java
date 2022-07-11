package com.example.progectkurs;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button tegGo;

    @FXML
    private Button tegGoGuest;

    @FXML
    private TextField tegLogin;

    @FXML
    private TextField tegPassword;

    @FXML
    private Button tegRegister;

    @FXML
    private Button tegSitata;

    @FXML
    private Button tegSitata1;

    @FXML
    void initialize() {


        tegRegister.setOnAction(actionEvent -> {
            tegRegister.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("registr.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Регистрация");
            stage.showAndWait();
        });

        tegGoGuest.setOnAction(actionEvent -> {
            tegGoGuest.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("guestList.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Гость");
            stage.showAndWait();
        });

        tegGo.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            String login = tegLogin.getText().trim();
            String password = tegPassword.getText().trim();
            String newPassword = null;
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] bytes = md5.digest(password.getBytes());


                StringBuilder sb = new StringBuilder();
                StringBuilder builder = new StringBuilder();
                for (byte password_hash : bytes) {
                    builder.append(String.format("%02X",password_hash));
                }
                System.out.print(builder.toString() + "          ");
                newPassword = builder.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            System.out.println(newPassword);

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2003_kurovoipgo",
                        "std_2003_kurovoipgo", "std_2003_kurovoipgo");
                Statement statement = connection.createStatement();
                String log;
                String pass;
                String root;
                ResultSet reg;
                try {
                    reg = statement.executeQuery("SELECT login, password , root, id, groups FROM users");
                    while(reg.next()) {
                        int i = 1;
                        log = reg.getString(1);
                        pass = reg.getString(2);
                        root = reg.getString(3);
                        DataLogin.ID = reg.getString(4);
                        DataLogin.Groups = reg.getString(5);

                        if (log.equals(login) && pass.equals(newPassword))
                        {
                            DataLogin saveLogin = new DataLogin(login);
                            System.out.println("Успешно");
                            if (root.equals("student"))
                                ChangeScene("sitata.fxml");
                            else if (root.equals("admin"))
                                ChangeScene("admin.fxml");
                            else if (root.equals("ver"))
                                ChangeScene("vereficator.fxml");
                            break;
                        }
                        else
                            System.out.print("");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }

    public void ChangeScene(String str) {
        tegGoGuest.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(str));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }
}
