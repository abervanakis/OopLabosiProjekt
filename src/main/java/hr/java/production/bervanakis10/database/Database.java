package hr.java.production.bervanakis10.database;

import hr.java.production.bervanakis10.model.*;
import hr.java.production.bervanakis10.utils.FxDialogsUtils;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class Database {
    private static Boolean activeConnectionWithDatabase = false;
    private static final String DATABASE_FILE = "dat/database.properties";
    private static Connection connectToDatabase() throws SQLException, IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(DATABASE_FILE));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Connection connection = DriverManager.getConnection(url, user,password);

        return connection;
    }
    public synchronized Optional<Item> findMostExpensiveItem() throws SQLException, IOException {
        while(activeConnectionWithDatabase) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        activeConnectionWithDatabase = true;

        Connection connection = connectToDatabase();

        String queryString =
        """
        SELECT * FROM ITEM
        ORDER BY SELLING_PRICE DESC
        LIMIT 1
        """;

        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery(queryString);

        while (resultSet.next()) {
            Long id = resultSet.getLong("ID");
            Long categoryId = resultSet.getLong("CATEGORY_ID");
            String name = resultSet.getString("NAME");
            BigDecimal width = resultSet.getBigDecimal("WIDTH");
            BigDecimal height = resultSet.getBigDecimal("HEIGHT");
            BigDecimal length = resultSet.getBigDecimal("LENGTH");
            BigDecimal productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
            BigDecimal sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");

            Item item = new Item(name, id, getCategoryWithID(categoryId), width, height, length, productionCost, sellingPrice, null);
            connection.close();
            activeConnectionWithDatabase = false;
            notifyAll();

            return Optional.of(item);
        }
        connection.close();
        activeConnectionWithDatabase = false;
        notifyAll();

        return Optional.empty();
    }
    public synchronized Optional<List<Item>> getAllItemsFromDatabaseSortedByPrice() throws SQLException, IOException {
        while(activeConnectionWithDatabase) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        activeConnectionWithDatabase = true;

        List<Item> itemList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                """
                    SELECT * FROM ITEM
                    ORDER BY SELLING_PRICE DESC
                    """);
        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            Long categoryId = resultSet.getLong("CATEGORY_ID");
            String name = resultSet.getString("NAME");
            BigDecimal width = resultSet.getBigDecimal("WIDTH");
            BigDecimal height = resultSet.getBigDecimal("HEIGHT");
            BigDecimal length = resultSet.getBigDecimal("LENGTH");
            BigDecimal productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
            BigDecimal sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");

            Item item = new Item(name, id, getCategoryWithID(categoryId), width, height, length, productionCost, sellingPrice, null);
            itemList.add(item);
        }
        connection.close();
        activeConnectionWithDatabase = false;
        notifyAll();

        return Optional.of(itemList);
    }
    public synchronized List<Address> getAllAddressesFromDatabase() throws SQLException, IOException {
        while(activeConnectionWithDatabase) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        activeConnectionWithDatabase = true;

        List<Address> addressList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ADDRESS");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String street = resultSet.getString("STREET");
            String houseNumber = resultSet.getString("HOUSE_NUMBER");
            String city = resultSet.getString("CITY");
            Integer postalCode = resultSet.getInt("POSTAL_CODE");

            Address address = new Address(id, street, houseNumber, city, Integer.toString(postalCode));
            addressList.add(address);
        }
        connection.close();
        activeConnectionWithDatabase = false;
        notifyAll();

        return addressList;
    }
    public synchronized List<Category> getAllCategoriesFromDatabase() throws SQLException, IOException {
        while(activeConnectionWithDatabase) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        activeConnectionWithDatabase = true;

        List<Category> categoryList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORY");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String description = resultSet.getString("DESCRIPTION");
            Category category = new Category(name, id, description);
            categoryList.add(category);
        }
        connection.close();
        activeConnectionWithDatabase = false;
        notifyAll();

        return categoryList;
    }
    public synchronized List<Item> getAllItemsFromDatabase() throws SQLException, IOException {
        while(activeConnectionWithDatabase) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        activeConnectionWithDatabase = true;

        List<Item> itemList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ITEM");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            Long categoryId = resultSet.getLong("CATEGORY_ID");
            String name = resultSet.getString("NAME");
            BigDecimal width = resultSet.getBigDecimal("WIDTH");
            BigDecimal height = resultSet.getBigDecimal("HEIGHT");
            BigDecimal length = resultSet.getBigDecimal("LENGTH");
            BigDecimal productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
            BigDecimal sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");

            Item item = new Item(name, id, getCategoryWithID(categoryId), width, height, length, productionCost, sellingPrice, null);
            itemList.add(item);
        }
        connection.close();
        activeConnectionWithDatabase = false;
        notifyAll();

        return itemList;
    }
    public synchronized List<Factory> getAllFactoriesFromDatabase() throws SQLException, IOException {
        while(activeConnectionWithDatabase) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        activeConnectionWithDatabase = true;

        List<Factory> factoryList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FACTORY");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            Long addressId = resultSet.getLong("ADDRESS_ID");

            Factory factory = new Factory(name, id, getAddressFromFactoryAddressId(addressId), getItemsFromFactoryWithId(id));
            factoryList.add(factory);
        }
        connection.close();
        activeConnectionWithDatabase = false;
        notifyAll();

        return factoryList;
    }
    public synchronized List<Store> getAllStoresFromDatabase() throws SQLException, IOException {
        while(activeConnectionWithDatabase) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        activeConnectionWithDatabase = true;

        List<Store> storeList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM STORE");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String webAddress = resultSet.getString("WEB_ADDRESS");


            Store store = new Store(name, id, webAddress, getItemsFromStoreWithId(id));
            storeList.add(store);
        }
        connection.close();
        activeConnectionWithDatabase = false;
        notifyAll();

        return storeList;
    }
    private static Set<Item> getItemsFromStoreWithId(Long storeId) throws SQLException, IOException {

        Set<Item> itemStoreSet = new HashSet<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM STORE_ITEM SI, ITEM I WHERE SI.STORE_ID = '" + storeId + "' AND SI.ITEM_ID = I.ID");

        while(resultSet.next()) {
            Long storeId2 = resultSet.getLong("STORE_ID");
            Long itemId = resultSet.getLong("ITEM_ID");
            Long itemId2 = resultSet.getLong("ID");
            Long categoryId = resultSet.getLong("CATEGORY_ID");
            String itemName = resultSet.getString("NAME");
            BigDecimal width = resultSet.getBigDecimal("WIDTH");
            BigDecimal height = resultSet.getBigDecimal("HEIGHT");
            BigDecimal length = resultSet.getBigDecimal("LENGTH");
            BigDecimal productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
            BigDecimal sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");

            Item item = new Item(itemName, itemId, getCategoryWithID(categoryId), width, height, length, productionCost, sellingPrice, null);
            itemStoreSet.add(item);
        }
        connection.close();

        return itemStoreSet;
    }
    private static List<Store> getStoresByNameAndWebAddress(String storeName, String storeWebAddress) throws SQLException, IOException {

        Database database = new Database();
        List<Store> storeList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM STORE WHERE NAME = '" + storeName + "' AND WEB_ADDRESS = '" + storeWebAddress + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String webAddress = resultSet.getString("WEB_ADDRESS");
            Store store = new Store(name, id, webAddress, database.getItemsFromStoreWithId(id));
            storeList.add(store);
        }
        connection.close();

        return storeList;
    }
    private synchronized List<Store> getStoreByName(String storeName) throws SQLException, IOException {

        Database database = new Database();
        List<Store> storeList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM STORE WHERE NAME = '" + storeName + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String webAddress = resultSet.getString("WEB_ADDRESS");
            Store store = new Store(name, id, webAddress, database.getItemsFromStoreWithId(id));
            storeList.add(store);
        }
        connection.close();

        return storeList;
    }
    private static List<Store> getStoreByWebAddress(String storeWebAddress) throws SQLException, IOException {

        Database database = new Database();
        List<Store> storeList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM STORE WHERE WEB_ADDRESS = '" + storeWebAddress + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String webAddress = resultSet.getString("WEB_ADDRESS");
            Store store = new Store(name, id, webAddress, database.getItemsFromStoreWithId(id));
            storeList.add(store);
        }
        connection.close();

        return storeList;
    }
    private static Address getAddressFromFactoryAddressId(Long addressId) throws SQLException, IOException {

        List<Address> addressList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ADDRESS WHERE ID = '" + addressId + "'");
        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String street = resultSet.getString("STREET");
            String houseNumber = resultSet.getString("HOUSE_NUMBER");
            String city = resultSet.getString("CITY");
            Integer postalCode = resultSet.getInt("POSTAL_CODE");

            Address address = new Address(id, street, houseNumber, city, Integer.toString(postalCode));
            addressList.add(address);
        }
        connection.close();

        return addressList.getFirst();
    }
    private static Set<Item> getItemsFromFactoryWithId(Long factoryId) throws SQLException, IOException {

        Set<Item> itemFactorySet = new HashSet<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FACTORY_ITEM FI, ITEM I WHERE FI.FACTORY_ID = '" + factoryId + "' AND FI.ITEM_ID = I.ID");

        while(resultSet.next()) {
            Long factoryId2 = resultSet.getLong("FACTORY_ID");
            Long itemId = resultSet.getLong("ITEM_ID");
            Long itemId2 = resultSet.getLong("ID");
            Long categoryId = resultSet.getLong("CATEGORY_ID");
            String itemName = resultSet.getString("NAME");
            BigDecimal width = resultSet.getBigDecimal("WIDTH");
            BigDecimal height = resultSet.getBigDecimal("HEIGHT");
            BigDecimal length = resultSet.getBigDecimal("LENGTH");
            BigDecimal productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
            BigDecimal sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");

            Item item = new Item(itemName, itemId, getCategoryWithID(categoryId), width, height, length, productionCost, sellingPrice, null);
            itemFactorySet.add(item);
        }
        connection.close();

        return itemFactorySet;
    }
    private static Long getCategoryIDWithName(String categoryName) throws SQLException, IOException {

        List<Category> categoryList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORY WHERE NAME = '" + categoryName + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String description = resultSet.getString("DESCRIPTION");
            Category category = new Category(name, id, description);
            categoryList.add(category);
        }
        connection.close();

        return categoryList.getFirst().getId();
    }
    private static Category getCategoryWithID(Long categoryId) throws SQLException, IOException {

        List<Category> categoryList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORY WHERE ID = '" + categoryId + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String description = resultSet.getString("DESCRIPTION");
            Category category = new Category(name, id, description);
            categoryList.add(category);
        }
        connection.close();

        return categoryList.getFirst();
    }
    private static List<Category> getCategoriesByName(String categoryName) throws SQLException, IOException {

        List<Category> categoryList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORY WHERE NAME = '" + categoryName + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String description = resultSet.getString("DESCRIPTION");
            Category category = new Category(name, id, description);
            categoryList.add(category);
        }
        connection.close();

        return categoryList;
    }

    private static List<Category> getCategoriesByDescription(String categoryDescription) throws SQLException, IOException {

        List<Category> categoryList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORY WHERE DESCRIPTION = '" + categoryDescription + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String description = resultSet.getString("DESCRIPTION");
            Category category = new Category(name, id, description);
            categoryList.add(category);
        }
        connection.close();

        return categoryList;
    }
    private static List<Category> getCategoriesByNameAndDescription(String categoryName, String categoryDescription) throws SQLException, IOException {

        List<Category> categoryList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORY WHERE NAME = '" + categoryName + "' AND DESCRIPTION = '" + categoryDescription + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String description = resultSet.getString("DESCRIPTION");
            Category category = new Category(name, id, description);
            categoryList.add(category);
        }
        connection.close();

        return categoryList;
    }
    private static List<Item> getItemsByName(String itemNameTextField) throws SQLException, IOException {

        Database database = new Database();
        List<Item> itemList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ITEM WHERE NAME = '" + itemNameTextField + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            Long categoryId = resultSet.getLong("CATEGORY_ID");
            String name = resultSet.getString("NAME");
            BigDecimal width = resultSet.getBigDecimal("WIDTH");
            BigDecimal height = resultSet.getBigDecimal("HEIGHT");
            BigDecimal length = resultSet.getBigDecimal("LENGTH");
            BigDecimal productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
            BigDecimal sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");

            Item item = new Item(name, id, database.getCategoryWithID(categoryId), width, height, length, productionCost, sellingPrice, null);
            itemList.add(item);
        }
        connection.close();

        return itemList;
    }
    private static List<Item> getItemsByCategory(String categoryChoiceBox) throws SQLException, IOException {

        Database database = new Database();
        List<Item> itemList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ITEM WHERE CATEGORY_ID = '" + database.getCategoryIDWithName(categoryChoiceBox) + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            Long categoryId = resultSet.getLong("CATEGORY_ID");
            String name = resultSet.getString("NAME");
            BigDecimal width = resultSet.getBigDecimal("WIDTH");
            BigDecimal height = resultSet.getBigDecimal("HEIGHT");
            BigDecimal length = resultSet.getBigDecimal("LENGTH");
            BigDecimal productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
            BigDecimal sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");

            Item item = new Item(name, id, database.getCategoryWithID(categoryId), width, height, length, productionCost, sellingPrice, null);
            itemList.add(item);
        }
        connection.close();

        return itemList;
    }
    private static List<Item> getItemsByNameAndCategory(String itemNameTextField, String categoryChoiceBox) throws SQLException, IOException {

        Database database = new Database();
        List<Item> itemList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ITEM WHERE NAME = '" + itemNameTextField + "' AND CATEGORY_ID = '" + database.getCategoryIDWithName(categoryChoiceBox) + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            Long categoryId = resultSet.getLong("CATEGORY_ID");
            String name = resultSet.getString("NAME");
            BigDecimal width = resultSet.getBigDecimal("WIDTH");
            BigDecimal height = resultSet.getBigDecimal("HEIGHT");
            BigDecimal length = resultSet.getBigDecimal("LENGTH");
            BigDecimal productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
            BigDecimal sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");

            Item item = new Item(name, id, database.getCategoryWithID(categoryId), width, height, length, productionCost, sellingPrice, null);
            itemList.add(item);
        }
        connection.close();

        return itemList;
    }
    private static List<Factory> getFactoriesByName(String factoryName) throws SQLException, IOException {

        Database database = new Database();
        List<Factory> factoryList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FACTORY WHERE NAME = '" + factoryName + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            Long address = resultSet.getLong("ADDRESS_ID");

            Factory factory = new Factory(name, id, database.getAddressFromFactoryAddressId(id), database.getItemsFromFactoryWithId(id));
            factoryList.add(factory);
        }
        connection.close();

        return factoryList;
    }
    private static List<Factory> getFactoriesByCity(String cityName) throws SQLException, IOException {

        Database database = new Database();
        List<Factory> factoryList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FACTORY WHERE ADDRESS_ID = '" + getFactoryAddressIdFromCityName(cityName) + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            Long address = resultSet.getLong("ADDRESS_ID");

            Factory factory = new Factory(name, id, database.getAddressFromFactoryAddressId(id), database.getItemsFromFactoryWithId(id));
            factoryList.add(factory);
        }
        connection.close();

        return factoryList;
    }
    private static List<Factory> getFactoriesByNameAndCity(String factoryName, String cityName) throws SQLException, IOException {

        Database database = new Database();
        List<Factory> factoryList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FACTORY WHERE NAME = '" + factoryName + "' AND ADDRESS_ID = '" + getFactoryAddressIdFromCityName(cityName) + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            Long address = resultSet.getLong("ADDRESS_ID");

            Factory factory = new Factory(name, id, database.getAddressFromFactoryAddressId(id), database.getItemsFromFactoryWithId(id));
            factoryList.add(factory);
        }
        connection.close();

        return factoryList;
    }
    private static Long getFactoryAddressIdFromCityName(String cityName) throws SQLException, IOException {

        List<Address> addressList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ADDRESS WHERE CITY = '" + cityName + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String street = resultSet.getString("STREET");
            String houseNumber = resultSet.getString("HOUSE_NUMBER");
            String city = resultSet.getString("CITY");
            Integer postalCode = resultSet.getInt("POSTAL_CODE");

            Address address = new Address(id, street, houseNumber, city, postalCode.toString());
            addressList.add(address);
        }
        connection.close();

        return addressList.getFirst().getId();
    }
    private static List<Item> getItemsByStoreName(String storeName) throws SQLException, IOException {

        Database database = new Database();
        List<Item> itemList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM STORE_ITEM SI, ITEM I WHERE SI.STORE_ID = '" + getStoreIdFromStoreName(storeName) + "' AND SI.ITEM_ID = I.ID");

        while(resultSet.next()) {
            Long storeId = resultSet.getLong("STORE_ID");
            Long itemId = resultSet.getLong("ITEM_ID");
            Long itemId2 = resultSet.getLong("ID");
            Long categoryId = resultSet.getLong("CATEGORY_ID");
            String itemName = resultSet.getString("NAME");
            BigDecimal width = resultSet.getBigDecimal("WIDTH");
            BigDecimal height = resultSet.getBigDecimal("HEIGHT");
            BigDecimal length = resultSet.getBigDecimal("LENGTH");
            BigDecimal productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
            BigDecimal sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");

            Item item = new Item(itemName, itemId, database.getCategoryWithID(categoryId), width, height, length, productionCost, sellingPrice, null);
            itemList.add(item);
        }
        connection.close();

        return itemList;
    }
    private static List<Item> getItemsByFactoryName(String factoryName) throws SQLException, IOException {

        Database database = new Database();
        List<Item> itemList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FACTORY_ITEM FI, ITEM I WHERE FI.FACTORY_ID = '" + getFactoryIdFromFactoryName(factoryName) + "' AND FI.ITEM_ID = I.ID");

        while(resultSet.next()) {
            Long factoryId = resultSet.getLong("FACTORY_ID");
            Long itemId = resultSet.getLong("ITEM_ID");
            Long itemId2 = resultSet.getLong("ID");
            Long categoryId = resultSet.getLong("CATEGORY_ID");
            String itemName = resultSet.getString("NAME");
            BigDecimal width = resultSet.getBigDecimal("WIDTH");
            BigDecimal height = resultSet.getBigDecimal("HEIGHT");
            BigDecimal length = resultSet.getBigDecimal("LENGTH");
            BigDecimal productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
            BigDecimal sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");

            Item item = new Item(itemName, itemId, database.getCategoryWithID(categoryId), width, height, length, productionCost, sellingPrice, null);
            itemList.add(item);
        }
        connection.close();

        return itemList;
    }
    private static Long getStoreIdFromStoreName(String storeName) throws SQLException, IOException {

        List<Store> storeList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM STORE WHERE NAME = '" + storeName + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String webAddress = resultSet.getString("WEB_ADDRESS");
            Store store = new Store(name, id, webAddress, null);
            storeList.add(store);
        }
        connection.close();

        return storeList.getFirst().getId();
    }
    private static Long getFactoryIdFromFactoryName(String factoryName) throws SQLException, IOException {

        List<Factory> factoryList = new ArrayList<>();
        Connection connection = connectToDatabase();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FACTORY WHERE NAME = '" + factoryName + "'");

        while(resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            Long address = resultSet.getLong("ADDRESS_ID");
            Factory factory = new Factory(name, id, null, null);
            factoryList.add(factory);
        }
        connection.close();

        return factoryList.getFirst().getId();
    }
    public static List<Category> searchCategories(String categoryNameTextField, String categoryDescriptionTextField) throws SQLException, IOException {

        List<Category> categoryList;
        Database database = new Database();

        if(categoryNameTextField.isEmpty() && categoryDescriptionTextField.isEmpty()) {
            categoryList = database.getAllCategoriesFromDatabase();
        }
        else if(!categoryNameTextField.isEmpty() && !categoryDescriptionTextField.isEmpty()) {
            categoryList = database.getCategoriesByNameAndDescription(categoryNameTextField, categoryDescriptionTextField);
        }
        else if(!categoryNameTextField.isEmpty()) {
            categoryList = database.getCategoriesByName(categoryNameTextField);
        }
        else {
            categoryList = database.getCategoriesByDescription(categoryDescriptionTextField);
        }

        return categoryList;
    }
    public static List<Item> searchItems(String itemNameTextField, String categoryChoiceBox, String storeName, String factoryName) throws SQLException, IOException {

        List<Item> itemList = new ArrayList<>();
        Database database = new Database();

        if(itemNameTextField.isEmpty() && categoryChoiceBox == null) {
            itemList = database.getAllItemsFromDatabase();
        }
        else if(!itemNameTextField.isEmpty() && categoryChoiceBox == null){
            itemList = database.getItemsByName(itemNameTextField);
        }
        else if(!itemNameTextField.isEmpty() && !categoryChoiceBox.isEmpty()) {
            itemList = database.getItemsByNameAndCategory(itemNameTextField, categoryChoiceBox);
        }
        else if(!categoryChoiceBox.isEmpty()) {
            itemList = database.getItemsByCategory(categoryChoiceBox);
        }

        if(!storeName.isEmpty() && factoryName.isEmpty()) {
            itemList = database.getItemsByStoreName(storeName);
        }
        else if(!factoryName.isEmpty() && storeName.isEmpty()) {
            itemList = database.getItemsByFactoryName(factoryName);
        }

        return itemList;
    }
    public static List<Factory> searchFactories(String factoryNameTextField, String cityChoiceBox) throws SQLException, IOException {

        Database database = new Database();
        List<Factory> factoryList = new ArrayList<>();

        if(factoryNameTextField.isEmpty() && cityChoiceBox == null) {
            factoryList = database.getAllFactoriesFromDatabase();
        }
        else if(!factoryNameTextField.isEmpty() && cityChoiceBox == null){
            factoryList = database.getFactoriesByName(factoryNameTextField);
        }
        else if(!factoryNameTextField.isEmpty() && !cityChoiceBox.isEmpty()) {
            factoryList = database.getFactoriesByNameAndCity(factoryNameTextField, cityChoiceBox);
        }
        else if(factoryNameTextField.isEmpty() && !(cityChoiceBox == null)) {
            factoryList = database.getFactoriesByCity(cityChoiceBox);
        }

        return factoryList;
    }
    public static List<Store> searchStore(String storeNameTextField, String storeWebAddressTextField) throws SQLException, IOException {

        Database database = new Database();
        List<Store> storeList;

        if(storeNameTextField.isEmpty() && storeWebAddressTextField.isEmpty()) {
            storeList = database.getAllStoresFromDatabase();
        }
        else if(!storeNameTextField.isEmpty() && !storeWebAddressTextField.isEmpty()) {
            storeList = database.getStoresByNameAndWebAddress(storeNameTextField, storeWebAddressTextField);
        }
        else if(!storeNameTextField.isEmpty()) {
            storeList = database.getStoreByName(storeNameTextField);
        }
        else {
            storeList = database.getStoreByWebAddress(storeWebAddressTextField);
        }

        return storeList;
    }
    public static void addCategory(String categoryNameTextField, String categoryDescriptionTextField) throws SQLException, IOException {

        Connection connection = connectToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CATEGORY(NAME, DESCRIPTION) VALUES(?,?)");
        preparedStatement.setString(1, categoryNameTextField);
        preparedStatement.setString(2, categoryDescriptionTextField);
        preparedStatement.executeUpdate();
        connection.close();

        Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Category added successfully!", "Category was added to file!", "Category '" + categoryNameTextField +"' was successfully added.");
        alert.showAndWait();

    }
    public static void addItem(String itemNameTextField, String categoryChoiceBox, String itemWidthTextField, String itemHeightTextField, String itemLengthTextField, String itemProductionCostTextField, String itemSellingPriceTextField) throws SQLException, IOException {

        Database database = new Database();
        Connection connection = connectToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ITEM(CATEGORY_ID, NAME, WIDTH, HEIGHT, LENGTH, PRODUCTION_COST, SELLING_PRICE) VALUES(?,?,?,?,?,?,?)");
        preparedStatement.setLong(1, database.getCategoryIDWithName(categoryChoiceBox));
        preparedStatement.setString(2, itemNameTextField);
        preparedStatement.setString(3, itemWidthTextField);
        preparedStatement.setString(4, itemHeightTextField);
        preparedStatement.setString(5, itemLengthTextField);
        preparedStatement.setString(6, itemProductionCostTextField);
        preparedStatement.setString(7, itemSellingPriceTextField);
        preparedStatement.executeUpdate();
        connection.close();

        Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Item added successfully!", "Item was added to file!", "Items with name '" + itemNameTextField + "' and category '" + categoryChoiceBox + "' was successfully added.");
        alert.showAndWait();
    }
    public static void addFactory(String factoryNameTextField, String factoryChoiceBox, ObservableList<String> selectedItems) throws SQLException, IOException {

        Database database = new Database();
        List<Item> itemList = database.getAllItemsFromDatabase();
        Set<Long> itemIds = new HashSet<>();
        for(String string : selectedItems) {
            for(Item item : itemList) {
                if(string.equals(item.getName())) {
                    itemIds.add(item.getId());
                }
            }
        }
        Connection connection = connectToDatabase();
        Long factoryAddressId = database.getFactoryAddressIdFromCityName(factoryChoiceBox);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO FACTORY(NAME, ADDRESS_ID) VALUES(?,?)");
        preparedStatement.setString(1, factoryNameTextField);
        preparedStatement.setLong(2, factoryAddressId);
        preparedStatement.executeUpdate();
        connection.close();

        Long factoryId = database.getFactoryIdFromFactoryName(factoryNameTextField);

        Connection connectionNovi = connectToDatabase();
        for(Long id : itemIds) {
            PreparedStatement preparedStatementForItems = connectionNovi.prepareStatement("INSERT INTO FACTORY_ITEM(FACTORY_ID, ITEM_ID) VALUES(?,?)");
            preparedStatementForItems.setLong(1, factoryId);
            preparedStatementForItems.setLong(2, id);
            preparedStatementForItems.executeUpdate();
        }
        connectionNovi.close();

        Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Factory added successfully!", "Factory was added to file!", "Factory '" + factoryNameTextField +"' was successfully added.");
        alert.showAndWait();
    }
    public static void addStore(String storeNameTextField, String storeWebAddressTextField, ObservableList<String> selectedItems) throws SQLException, IOException {
        Database database = new Database();
        List<Item> itemList = database.getAllItemsFromDatabase();
        Set<Long> itemIds = new HashSet<>();
        for(String string : selectedItems) {
            for(Item item : itemList) {
                if(string.equals(item.getName())) {
                    itemIds.add(item.getId());
                }
            }
        }
        Connection connection = connectToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO STORE(NAME, WEB_ADDRESS) VALUES(?,?)");
        preparedStatement.setString(1, storeNameTextField);
        preparedStatement.setString(2, storeWebAddressTextField);
        preparedStatement.executeUpdate();
        connection.close();

        Long storeId = database.getStoreIdFromStoreName(storeNameTextField);

        Connection connectionNovi = connectToDatabase();
        for(Long id : itemIds) {
            PreparedStatement preparedStatementForItems = connectionNovi.prepareStatement("INSERT INTO STORE_ITEM(STORE_ID, ITEM_ID) VALUES(?,?)");
            preparedStatementForItems.setLong(1, storeId);
            preparedStatementForItems.setLong(2, id);
            preparedStatementForItems.executeUpdate();
        }
        connectionNovi.close();

        Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Store added successfully!", "Store was added to file!", "Store '" + storeNameTextField +"' was successfully added.");
        alert.showAndWait();
    }
}
