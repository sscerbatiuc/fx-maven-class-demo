module fx.demo {
    requires javafx.fxml;
    requires  javafx.controls;
    requires java.sql;

    opens it.step.controller to javafx.fxml;
    opens it.step.model to javafx.base;

    opens it.step;
}