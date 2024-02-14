package hr.java.production.bervanakis10;

import hr.java.production.bervanakis10.database.Database;
import hr.java.production.bervanakis10.model.Item;
import hr.java.production.bervanakis10.model.Store;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class StoreSearchScreenController {
    @FXML
    private TextField storeNameTextField;
    @FXML
    private TextField storeWebAddressTextField;
    @FXML
    private TableView<Store> storeTableView;
    @FXML
    private TableColumn<Store, String> storeNameTableColumn;
    @FXML
    private TableColumn<Store, String> storeWebAddressTableColumn;
    @FXML
    private TableColumn<Store, String> storeItemsTableColumn;

    public void initialize() {
        storeNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        storeWebAddressTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWebAddress()));
        storeItemsTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItems().stream().map(Item::getName).collect(Collectors.joining(", "))));
        }
    public void searchStores() throws SQLException, IOException {
        //Podaci iz databaze
        ObservableList<Store> observableStoreList = FXCollections.observableArrayList(Database.searchStore(storeNameTextField.getText(), storeWebAddressTextField.getText()));
        //Podaci iz datoteke
        //ObservableList<Store> observableStoreList = FXCollections.observableArrayList(FileUtils.searchStore(storeNameTextField.getText(), storeWebAddressTextField.getText(), storeWebAddressTableColumn.getText()));
        storeTableView.setItems(observableStoreList);
    }
}
