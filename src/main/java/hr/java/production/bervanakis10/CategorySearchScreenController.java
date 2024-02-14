package hr.java.production.bervanakis10;

import hr.java.production.bervanakis10.database.Database;
import hr.java.production.bervanakis10.model.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class CategorySearchScreenController {
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private TextField categoryDescriptionTextField;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TableColumn<Category, String> categoryNameTableColumn;
    @FXML
    private TableColumn<Category, String> categoryDescriptionTableColumn;

    public void initialize() {
        categoryNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        categoryDescriptionTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
    }
    public void searchCategories() throws SQLException, IOException {
        //Podaci iz databaze
        ObservableList<Category> observableCategoryList = FXCollections.observableArrayList(Database.searchCategories(categoryNameTextField.getText(), categoryDescriptionTextField.getText()));
        //Podaci iz datoteke
        //ObservableList<Category> observableCategoryList = FXCollections.observableArrayList(FileUtils.searchCategories(categoryNameTextField.getText(), categoryDescriptionTextField.getText()));
        categoryTableView.setItems(observableCategoryList);
    }
}
