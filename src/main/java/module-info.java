module com.example.progectkurs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.progectkurs to javafx.fxml;
    exports com.example.progectkurs;
}