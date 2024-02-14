package hr.java.production.bervanakis10;

import hr.java.production.bervanakis10.database.Database;
import hr.java.production.bervanakis10.model.*;
import hr.java.production.bervanakis10.utils.FileUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddNewStoreScreenController {
    @FXML
    private TextField storeNameTextField;
    @FXML
    private TextField storeWebAddressTextField;
    @FXML
    private ListView<String> storeItemsListView;

    public void initialize() {
        addItemsToListView();
        storeItemsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    public void addItemsToListView() {
        List<Category> categoryList = FileUtils.readCategories();
        List<Item> itemList = FileUtils.readItems(categoryList);
        List<String> itemNamesList = new ArrayList<>();
        for(Item item : itemList) {
            itemNamesList.add(item.getName());
        }
        storeItemsListView.getItems().addAll(itemNamesList);
    }
    public void addNewStore() throws SQLException, IOException {
        ObservableList<String> selectedItems = storeItemsListView.getSelectionModel().getSelectedItems();
        //Podaci za databazu
        Database.addStore(storeNameTextField.getText(), storeWebAddressTextField.getText(), selectedItems);
        //Podaci za dototeku
        //FileUtils.addStore(storeNameTextField.getText(), storeWebAddressTextField.getText(), selectedItems);
    }
}
