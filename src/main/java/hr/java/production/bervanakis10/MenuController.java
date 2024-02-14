package hr.java.production.bervanakis10;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuController {

    public void showItemSearchScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("itemSearchScreen2.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Main.getMainStage().setTitle("Item search screen");
        Main.getMainStage().setScene(scene);
        Main.getMainStage().show();
    }
    public void showCategorySearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("categorySearchScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Main.getMainStage().setTitle("Category search screen");
        Main.getMainStage().setScene(scene);
        Main.getMainStage().show();
    }
    public void showFactorySearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("factorySearchScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Main.getMainStage().setTitle("Factory search screen");
        Main.getMainStage().setScene(scene);
        Main.getMainStage().show();
    }
    public void showStoreSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("storeSearchScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Main.getMainStage().setTitle("Store search screen");
        Main.getMainStage().setScene(scene);
        Main.getMainStage().show();
    }
    public void showAddNewCategoryScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addNewCategoryScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Main.getMainStage().setTitle("Add new category screen");
        Main.getMainStage().setScene(scene);
        Main.getMainStage().show();
    }
    public void showAddNewItemScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addNewItemScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Main.getMainStage().setTitle("Add new item screen");
        Main.getMainStage().setScene(scene);
        Main.getMainStage().show();
    }
    public void showAddNewFactoryScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addNewFactoryScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Main.getMainStage().setTitle("Add new factory screen");
        Main.getMainStage().setScene(scene);
        Main.getMainStage().show();
    }
    public void showAddNewStoreScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addNewStoreScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Main.getMainStage().setTitle("Add new store screen");
        Main.getMainStage().setScene(scene);
        Main.getMainStage().show();
    }
}
