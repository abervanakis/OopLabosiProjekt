package hr.java.production.bervanakis10.threads;

import hr.java.production.bervanakis10.Main;
import hr.java.production.bervanakis10.database.Database;
import hr.java.production.bervanakis10.model.Item;
import javafx.application.Platform;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class SortingItemsThread implements Runnable {
    Database database;
    public SortingItemsThread() {
        database = new Database();
    }

    @Override
    public void run() {
        while(true) {
            try {
                Optional<Item> mostExpensiveItemOptional = database.findMostExpensiveItem();
                if(mostExpensiveItemOptional.isPresent()) {
                    Platform.runLater(() -> {
                        Main.getMainStage().setTitle("The most expensive item is " + mostExpensiveItemOptional.get().getName() + " with price " + mostExpensiveItemOptional.get().getSellingPrice());
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
