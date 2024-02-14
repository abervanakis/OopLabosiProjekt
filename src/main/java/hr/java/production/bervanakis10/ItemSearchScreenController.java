package hr.java.production.bervanakis10;

import hr.java.production.bervanakis10.database.Database;
import hr.java.production.bervanakis10.model.Category;
import hr.java.production.bervanakis10.model.Item;
import hr.java.production.bervanakis10.threads.SortingItemsByPriceThread;
import hr.java.production.bervanakis10.threads.SortingItemsThread;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItemSearchScreenController {
    @FXML
    private TextField itemNameTextField;
    @FXML
    private TextField itemSearchByStoreNameTextField;
    @FXML
    private TextField itemSearchByFactoryNameTextField;
    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private TableView<Item> itemTableView;
    @FXML
    private TableColumn<Item, String> itemNameTableColumn;
    @FXML
    private TableColumn<Item, String> itemCategoryTableColumn;
    @FXML
    private TableColumn<Item, String> itemWidthTableColumn;
    @FXML
    private TableColumn<Item, String> itemHeightTableColumn;
    @FXML
    private TableColumn<Item, String> itemLengthTableColumn;
    @FXML
    private TableColumn<Item, String> itemProductionCostTableColumn;
    @FXML
    private TableColumn<Item, String> itemSellingPriceTableColumn;

    public void initialize() throws SQLException, IOException {
        itemNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        itemCategoryTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getName()));
        itemWidthTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWidth().toString()));
        itemHeightTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHeight().toString()));
        itemLengthTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLenght().toString()));
        itemProductionCostTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductionCost().toString()));
        itemSellingPriceTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSellingPrice().toString()));
        addChoicesToCategoryChoiceBox();


        SortingItemsThread thread1 = new SortingItemsThread();
        Thread starter1 = new Thread(thread1);
        starter1.start();

        SortingItemsByPriceThread thread2 = new SortingItemsByPriceThread(itemTableView);
        Thread starter2 = new Thread(thread2);
        starter2.start();
    }
    public void searchItems() throws SQLException, IOException {
        //Podaci iz databaze
        ObservableList<Item> observableItemList = FXCollections.observableArrayList(Database.searchItems(itemNameTextField.getText(), categoryChoiceBox.getValue(), itemSearchByStoreNameTextField.getText(), itemSearchByFactoryNameTextField.getText()));
        //Podaci iz datoteke
        //ObservableList<Item> observableItemList = FXCollections.observableArrayList(FileUtils.searchItems(itemNameTextField.getText(), categoryChoiceBox.getValue()));
        itemTableView.setItems(observableItemList);
    }
    public void addChoicesToCategoryChoiceBox() throws SQLException, IOException {
        //Podaci iz databaze
        Database database = new Database();
        List<Category> categoryList = database.getAllCategoriesFromDatabase();
        for(Category category : categoryList) {
            categoryChoiceBox.getItems().add(category.getName());
        }
        //Podaci iz datoteke
        /*List<Category> categoryList = FileUtils.readCategories();
        for(Category category : categoryList) {
            categoryChoiceBox.getItems().add(category.getName());
        }*/
    }
    /*public void searchItemsByPrice() throws SQLException, IOException {
        Database database = new Database();
        ObservableList<Item> observableList = FXCollections.observableArrayList(database.getAllItemsFromDatabaseSortedByPrice());
        itemTableView.setItems(observableList);
    }
    public void setItemListToTable(List<Item> itemList) {
        ObservableList<Item> observableItemList = FXCollections.observableArrayList(itemList);
        itemTableView.setItems(observableItemList);
    }*/
}