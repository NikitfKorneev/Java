package com.example.progectkurs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button tegExit;

    @FXML
    private Button tegSitata;

    @FXML
    void initialize() {
        tegExit.setOnAction(actionEvent -> {
            tegExit.getScene().getWindow().hide();
            Main.mainStage.show();//Хорошо работает
        });


    }

}
