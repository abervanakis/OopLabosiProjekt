package hr.java.production.bervanakis10.utils;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class FxUtils {
    public static TextFormatter<String> getIntegerTextFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if(text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        return textFormatter;
    }
}
