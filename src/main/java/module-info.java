module app {
    requires javafx.controls;
    requires javafx.fxml;

    opens app to javafx.fxml;
    exports app;

    opens entities to javafx.base;
    exports app.controllers;
    opens app.controllers to javafx.fxml;
}