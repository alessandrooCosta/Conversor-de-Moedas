module com.example.testejavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens principal to javafx.fxml;
    exports principal;
}