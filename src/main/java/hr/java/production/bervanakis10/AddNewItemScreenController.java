package hr.java.production.bervanakis10;

import hr.java.production.bervanakis10.database.Database;
import hr.java.production.bervanakis10.model.Category;
import hr.java.production.bervanakis10.utils.FxUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddNewItemScreenController {
    @FXML
    private TextField itemNameTextField;
    @FXML
    private TextField itemWidthTextField;
    @FXML
    private TextField itemHeightTextField;
    @FXML
    private TextField itemLengthTextField;
    @FXML
    private TextField itemProductionCostTextField;
    @FXML
    private TextField itemSellingPriceTextField;
    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    public void initialize() throws SQLException, IOException {
        addChoicesToCategoryChoiceBox();
        itemWidthTextField.setTextFormatter(FxUtils.getIntegerTextFormatter());
        itemHeightTextField.setTextFormatter(FxUtils.getIntegerTextFormatter());
        itemLengthTextField.setTextFormatter(FxUtils.getIntegerTextFormatter());
        itemProductionCostTextField.setTextFormatter(FxUtils.getIntegerTextFormatter());
        itemSellingPriceTextField.setTextFormatter(FxUtils.getIntegerTextFormatter());
    }
    public void addChoicesToCategoryChoiceBox() throws SQLException, IOException {
        //Podaci za databazu
        Database database = new Database();
        List<Category> categoryList = database.getAllCategoriesFromDatabase();
        //Podaci za dototeku
        //List<Category> categoryList = FileUtils.readCategories();
        for(Category category : categoryList) {
            categoryChoiceBox.getItems().add(category.getName());
        }
    }
    public void addNewItem() throws SQLException, IOException {
        //Podaci za databazu
        Database.addItem(itemNameTextField.getText(), categoryChoiceBox.getValue(), itemWidthTextField.getText(), itemHeightTextField.getText(), itemLengthTextField.getText(), itemProductionCostTextField.getText(), itemSellingPriceTextField.getText());
        //Podaci za datoteku
        //FileUtils.addItem(itemNameTextField.getText(), categoryChoiceBox.getValue(), itemWidthTextField.getText(), itemHeightTextField.getText(), itemLengthTextField.getText(), itemProductionCostTextField.getText(), itemSellingPriceTextField.getText());
    }
}
