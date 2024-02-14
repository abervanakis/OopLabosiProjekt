package hr.java.production.bervanakis10;

import hr.java.production.bervanakis10.database.Database;
import hr.java.production.bervanakis10.model.Address;
import hr.java.production.bervanakis10.model.Category;
import hr.java.production.bervanakis10.model.Item;
import hr.java.production.bervanakis10.utils.FileUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddNewFactoryScreenController {
    @FXML
    private TextField factoryNameTextField;
    @FXML
    private ChoiceBox<String> factoryChoiceBox;
    @FXML
    private ListView<String> factoryItemsListView;

    public void initialize() throws SQLException, IOException {
        addItemsToListView();
        addChoicesToFactoryChoiceBox();
        factoryItemsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addItemsToListView() {
        List<Category> categoryList = FileUtils.readCategories();
        List<Item> itemList = FileUtils.readItems(categoryList);
        List<String> itemNamesList = new ArrayList<>();
        for(Item item : itemList) {
            itemNamesList.add(item.getName());
        }
        factoryItemsListView.getItems().addAll(itemNamesList);
    }
    public void addNewFactory() throws SQLException, IOException {
        ObservableList<String> selectedItems = factoryItemsListView.getSelectionModel().getSelectedItems();
        //Podaci za databazu
        Database.addFactory(factoryNameTextField.getText(), factoryChoiceBox.getValue(), selectedItems);
        //Podaci za dototeku
        //FileUtils.addFactory(factoryNameTextField.getText(), factoryChoiceBox.getValue(), selectedItems);
    }
    public void addChoicesToFactoryChoiceBox() throws SQLException, IOException {
        //Podaci za databazu
        Database database = new Database();
        List<Address> addressList = database.getAllAddressesFromDatabase();
        //Podaci za dototeku
        //List<Address> addressList = FileUtils.readAddresses();
        for(Address address : addressList) {
            factoryChoiceBox.getItems().add(address.getCity());
        }
    }
}
