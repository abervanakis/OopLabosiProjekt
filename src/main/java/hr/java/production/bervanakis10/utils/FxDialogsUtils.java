package hr.java.production.bervanakis10.utils;

import javafx.scene.control.Alert;

public class FxDialogsUtils {
    public static Alert getAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }
}
