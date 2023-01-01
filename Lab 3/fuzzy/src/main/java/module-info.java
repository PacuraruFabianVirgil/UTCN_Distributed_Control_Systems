module com.example.fuzzy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fuzzy to javafx.fxml;
    exports com.example.fuzzy;
}