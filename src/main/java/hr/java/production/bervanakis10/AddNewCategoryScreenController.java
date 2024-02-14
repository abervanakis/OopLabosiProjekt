package hr.java.production.bervanakis10;

import hr.java.production.bervanakis10.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class AddNewCategoryScreenController {
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private TextField categoryDescriptionTextField;

    public void initialize() {

    }
    public void addNewCategory() throws SQLException, IOException {
        //Podaci za databazu
        Database.addCategory(categoryNameTextField.getText(), categoryDescriptionTextField.getText());
        //Podaci za datoteku
        //FileUtils.addCategory(categoryNameTextField.getText(), categoryDescriptionTextField.getText());
    }
}
