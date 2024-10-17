module com.example.testejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens view to javafx.fxml;
    exports view;
    exports controller;
    opens controller to javafx.fxml;
    opens model to com.google.gson;
}