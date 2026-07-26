module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    requires transitive javafx.graphics;
    opens com.example.controller to javafx.fxml;

    opens com.example to javafx.graphics;
    exports com.example;
}
