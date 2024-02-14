package hr.java.production.bervanakis10;

import hr.java.production.bervanakis10.database.Database;
import hr.java.production.bervanakis10.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class FactorySearchScreenController {
    @FXML
    private TextField factoryNameTextField;
    @FXML
    private ChoiceBox<String> cityChoiceBox;
    @FXML
    private TableView<Factory> factoryTableView;
    @FXML
    private TableColumn<Factory, String> factoryNameTableColumn;
    @FXML
    private TableColumn<Factory, String> factoryCityTableColumn;
    @FXML
    private TableColumn<Factory, String> factoryItemsTableColumn;

    public void initialize() throws SQLException, IOException {
        factoryNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        factoryCityTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getCity()));
        factoryItemsTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItems().stream().map(Item::getName).collect(Collectors.joining(", "))));
        addChoicesToCityChoiceBox();
    }
    public void searchFactories() throws SQLException, IOException {
        //Podaci iz databaze
        ObservableList<Factory> observableFactoryList = FXCollections.observableArrayList(Database.searchFactories(factoryNameTextField.getText(), cityChoiceBox.getValue()));
        //Podaci iz datoteke
        //ObservableList<Factory> observableFactoryList = FXCollections.observableArrayList(FileUtils.searchFactories(factoryNameTextField.getText(), cityChoiceBox.getValue()));
        factoryTableView.setItems(observableFactoryList);
    }
    public void addChoicesToCityChoiceBox() throws SQLException, IOException {
        //Podaci iz databaze
        Database database = new Database();
        List<Address> addressList = database.getAllAddressesFromDatabase();
        for(Address address : addressList) {
            cityChoiceBox.getItems().add(address.getCity());
        }
        //Podaci iz datoteke
        /*List<Address> addressList = FileUtils.readAddresses();
        for(Address address : addressList) {
            cityChoiceBox.getItems().add(address.getCity());
        }*/
    }
}
