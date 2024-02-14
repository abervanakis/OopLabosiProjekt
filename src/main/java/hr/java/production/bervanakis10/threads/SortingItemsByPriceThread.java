package hr.java.production.bervanakis10.threads;

import hr.java.production.bervanakis10.database.Database;
import hr.java.production.bervanakis10.model.Item;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SortingItemsByPriceThread implements Runnable {

    private TableView<Item> tableView;
    Database database;
    public SortingItemsByPriceThread(TableView<Item> tableView) {
        this.tableView = tableView;
        database = new Database();
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Optional<List<Item>> sortedItemList = database.getAllItemsFromDatabaseSortedByPrice();
                if(sortedItemList.isPresent()) {
                    Platform.runLater(() -> {
                        ObservableList<Item> itemObservableList = FXCollections.observableList(sortedItemList.get());
                        tableView.setItems(itemObservableList);
                    });
                }
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
