module com.example.os {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.os to javafx.fxml;
    exports com.example.os;
}