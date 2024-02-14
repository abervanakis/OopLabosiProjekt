module hr.java.production.bervanakis10 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens hr.java.production.bervanakis10 to javafx.fxml;
    exports hr.java.production.bervanakis10;
}