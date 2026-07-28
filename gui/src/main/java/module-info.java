module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    requires transitive javafx.graphics;

    opens com.example.controller to javafx.fxml;

    opens com.example to javafx.graphics;

    opens com.example.model to javafx.fxml; // added for custom class (NumberTextField.java)

    exports com.example;
}
