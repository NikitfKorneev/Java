package com.example.progectkurs;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class singUP {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField teg2Name;

    @FXML
    private TextField teg3Name;

    @FXML
    private Button tegBack;

    @FXML
    private Button tegGoRegister;

    @FXML
    private TextField tegGroup;

    @FXML
    private TextField tegLogin;

    @FXML
    private TextField tegName;

    @FXML
    private TextField tegPassword;

    @FXML
    private TextField tegUniver;


    @FXML
    void initialize() {
        tegBack.setOnAction(actionEvent -> {
            tegBack.getScene().getWindow().hide();
            Main.mainStage.show();//Хорошо работает
        });
        tegGoRegister.setOnAction(actionEvent -> {
            String Name = tegName.getText().trim();
            String Name2 =  teg2Name.getText().trim();
            String Name3 =  teg3Name.getText().trim();
            String login = tegLogin.getText().trim();
            String password = tegPassword.getText().trim();
            String group = tegGroup.getText().trim();
            String univer = tegUniver.getText().trim();
            String lim_password = null;
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] bytes = md5.digest(password.getBytes());

                StringBuilder sb = new StringBuilder();
                StringBuilder builder = new StringBuilder();
                for (byte password_hash : bytes) {
                    builder.append(String.format("%02X",password_hash));
                    //last_password_hash = password_hash;
                }
                System.out.print(builder.toString() + "          ");
                lim_password = builder.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            if(!login.equals("") && ! password.equals("") && ! Name.equals("") && ! Name2.equals("") && ! Name3.equals("") && !group.equals("") && !univer.equals(""))
                loginUsers(Name,Name2,Name3,group,univer,login,lim_password);
            else
                System.out.println("Заполните поля");
        });
    }

    private void loginUsers(String login, String password,String Name,String Name2,String Name3,String group,String univer) {
        String _Name = Name;
        String _Name2 =  Name2;
        String _Name3 =  Name3;
        String _login = login;
        String _password = password;
        String _group = group;
        String _univer = univer;
        String db = "users";
        int id = VPN.AIID + 1;
            VPN.mainRegist(Name, Name2, Name3, group, univer, login, password, db, id);
        }
}