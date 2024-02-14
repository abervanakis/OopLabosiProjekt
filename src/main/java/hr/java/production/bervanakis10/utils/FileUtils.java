package hr.java.production.bervanakis10.utils;

import hr.java.production.bervanakis10.exception.FileSaveException;
import hr.java.production.bervanakis10.model.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class FileUtils {
    public static final String CATEGORY_FILE_LOCATION = "dat/categories.txt";
    public static final String ITEM_FILE_LOCATION = "dat/items.txt";
    public static final String FACTORY_FILE_LOCATION = "dat/factories.txt";
    public static final String STORE_FILE_LOCATION = "dat/stores.txt";
    public static final String ADDRESS_FILE_LOCATION = "dat/addresses.txt";
    public static List<Category> readCategories() {
        List<Category> categoryList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CATEGORY_FILE_LOCATION))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Integer.toUnsignedLong(Integer.parseInt((line)));
                String categoryName = bufferedReader.readLine();
                String categoryDescription = bufferedReader.readLine();

                Category category = new Category(categoryName, id, categoryDescription);
                categoryList.add(category);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }
    public static List<Item> readItems(List<Category> categories) {
        List<Item> itemList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ITEM_FILE_LOCATION))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Integer.toUnsignedLong(Integer.parseInt((line)));
                String itemName = bufferedReader.readLine();
                String category = bufferedReader.readLine();
                BigDecimal width = BigDecimal.valueOf(Long.parseLong(bufferedReader.readLine()));
                BigDecimal height = BigDecimal.valueOf(Long.parseLong(bufferedReader.readLine()));
                BigDecimal lenght = BigDecimal.valueOf(Long.parseLong(bufferedReader.readLine()));
                BigDecimal productionCost = BigDecimal.valueOf(Long.parseLong(bufferedReader.readLine()));
                BigDecimal sellingPrice = BigDecimal.valueOf(Long.parseLong(bufferedReader.readLine()));
                Discount discountAmount = new Discount(BigDecimal.valueOf(Long.parseLong(bufferedReader.readLine())));

                Long categoryLong = Integer.toUnsignedLong(Integer.parseInt((category)));
                Category itemCategory = null;
                for(Category category1 : categories) {
                    if(category1.getId().equals(categoryLong)) {
                        itemCategory = category1;
                    }
                }
                Item item = new Item(itemName, id, itemCategory, width, height, lenght, productionCost, sellingPrice, discountAmount);
                itemList.add(item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }
    public static List<Address> readAddresses() {
        List<Address> addressList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ADDRESS_FILE_LOCATION))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Integer.toUnsignedLong(Integer.parseInt((line)));
                String street = bufferedReader.readLine();
                String houseNumber = bufferedReader.readLine();
                String city = bufferedReader.readLine();
                String postalCode = bufferedReader.readLine();

                Address address = new Address(id, street, houseNumber, city, postalCode);
                addressList.add(address);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return addressList;
    }
    public static List<Factory> readFactories(List<Item> items, List<Address> addresses) {
        List<Factory> factories = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FACTORY_FILE_LOCATION))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Integer.toUnsignedLong(Integer.parseInt((line)));
                String factoryName = bufferedReader.readLine();
                String factoryAddress = bufferedReader.readLine();
                String itemIds = bufferedReader.readLine();

                Long longFactoryAddress = Integer.toUnsignedLong(Integer.parseInt((factoryAddress)));

                Set<Integer> integerList = Arrays.stream(itemIds.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());

                Address addressOfFactory = null;
                for(Address address : addresses) {
                    if(longFactoryAddress.equals(address.getId())) {
                        addressOfFactory = address;
                    }
                }

                Set<Item> itemsInFactory = new HashSet<>();
                for(Item item : items) {
                    if(integerList.contains(item.getId().intValue())) {
                        itemsInFactory.add(item);
                    }
                }

                Factory factory = new Factory(factoryName, id, addressOfFactory, itemsInFactory);
                factories.add(factory);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return factories;
    }
    public static List<Store> readStores(List<Item> items) {
        List<Store> stores = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(STORE_FILE_LOCATION))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Integer.toUnsignedLong(Integer.parseInt((line)));
                String storeName = bufferedReader.readLine();
                String storeWebAddress = bufferedReader.readLine();
                String itemIds = bufferedReader.readLine();

                Set<Integer> integerList = Arrays.stream(itemIds.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());

                Set<Item> itemsInStore = new HashSet<>();
                for(Item item : items) {
                    if(integerList.contains(item.getId().intValue())) {
                        itemsInStore.add(item);
                    }
                }
                Store store = new Store(storeName, id, storeWebAddress, itemsInStore);
                stores.add(store);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stores;
    }
    public static void saveCategories(List<Category> categoryList) throws FileSaveException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CATEGORY_FILE_LOCATION))) {

            for(Category category : categoryList) {
                pw.println(category.getId());
                pw.println(category.getName());
                pw.println(category.getDescription());
            }
        } catch (IOException e) {
            throw new FileSaveException("Error while saving categories to file!", e);
        }
    }
    public static void saveItems(List<Item> itemList) throws FileSaveException{
        try (PrintWriter pw = new PrintWriter(new FileWriter(ITEM_FILE_LOCATION))) {

            for(Item item : itemList) {
                pw.println(item.getId());
                pw.println(item.getName());
                pw.println(item.getCategory().getId());
                pw.println(item.getWidth());
                pw.println(item.getHeight());
                pw.println(item.getLenght());
                pw.println(item.getProductionCost());
                pw.println(item.getSellingPrice());
                pw.println(item.getDiscountAmount().discountAmount());
            }
        } catch (IOException e) {
            throw new FileSaveException("Error while saving items to file!", e);
        }
    }
    public static void saveFactories(List<Factory> factoryList) throws FileSaveException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FACTORY_FILE_LOCATION))) {

            for(Factory factory : factoryList) {
                pw.println(factory.getId());
                pw.println(factory.getName());
                pw.println(factory.getAddress().getId());

                StringBuilder stringBuilder = new StringBuilder();
                for(Item item : factory.getItems()) {
                    stringBuilder.append(Long.toString(item.getId()));
                    stringBuilder.append(",");
                }
                String string = stringBuilder.toString();
                pw.println(string);
            }
        } catch (IOException e) {
            throw new FileSaveException("Error while saving factories to file!", e);
        }
    }
    public static void saveStores(List<Store> storeList) throws FileSaveException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(STORE_FILE_LOCATION))) {

            for(Store store : storeList) {
                pw.println(store.getId());
                pw.println(store.getName());
                pw.println(store.getWebAddress());

                StringBuilder stringBuilder = new StringBuilder();
                for(Item item : store.getItems()) {
                    stringBuilder.append(Long.toString(item.getId()));
                    stringBuilder.append(",");
                }
                String string = stringBuilder.toString();
                pw.println(string);
            }
        } catch (IOException e) {
            throw new FileSaveException("Error while saving stores to file!", e);
        }
    }
    public static Long generateNewCategoryId() {
        Optional<Category> categoryOptional = readCategories().stream().max((c1, c2) -> c1.getId().compareTo(c2.getId()));
        if(categoryOptional.isPresent()) {
            return categoryOptional.get().getId() + 1;
        }
        else {
            return (long) 1;
        }
    }
    public static Long generateNewItemId() {
        List<Category> categoryList = readCategories();
        Optional<Item> itemOptional = readItems(categoryList).stream().max((c1, c2) -> c1.getId().compareTo(c2.getId()));
        if(itemOptional.isPresent()) {
            return itemOptional.get().getId() + 1;
        }
        else {
            return (long) 1;
        }
    }
    public static Long generateNewFactoryId() {
        List<Category> categoryList = readCategories();
        List<Item> itemList = readItems(categoryList);

        Optional<Factory> factoryOptional = readFactories(itemList, readAddresses()).stream().max((c1, c2) -> c1.getId().compareTo(c2.getId()));
        if(factoryOptional.isPresent()) {
            return factoryOptional.get().getId() + 1;
        }
        else {
            return (long) 1;
        }
    }
    public static Long generateNewStoreId() {
        List<Category> categoryList = readCategories();
        List<Item> itemList = readItems(categoryList);

        Optional<Store> storeOptional = readStores(itemList).stream().max((c1, c2) -> c1.getId().compareTo(c2.getId()));
        if(storeOptional.isPresent()) {
            return storeOptional.get().getId() + 1;
        }
        else {
            return (long) 1;
        }
    }
    public static List<Category> searchCategories(String categoryNameTextField, String categoryDescriptionTextField) {
        List<Category> categoryList = FileUtils.readCategories();

        if(Optional.ofNullable(categoryNameTextField).isPresent() && Optional.ofNullable(categoryDescriptionTextField).isEmpty()) {
            categoryList = categoryList.stream().filter(c -> c.getName().contains(categoryNameTextField)).toList();
        }
        else if(Optional.ofNullable(categoryDescriptionTextField).isPresent() && Optional.ofNullable(categoryNameTextField).isEmpty()) {
            categoryList = categoryList.stream().filter(c -> c.getName().contains(categoryDescriptionTextField)).toList();
        }
        else if(Optional.ofNullable(categoryNameTextField).isPresent() && Optional.ofNullable(categoryDescriptionTextField).isPresent()) {
            categoryList = categoryList.stream().filter(c -> c.getName().contains(categoryNameTextField) && c.getDescription().contains(categoryDescriptionTextField)).toList();
        }
        return categoryList;
    }
    public static List<Item> searchItems(String itemNameTextField, String categoryChoiceBox) {
        List<Category> categoryList = FileUtils.readCategories();
        List<Item> itemList = FileUtils.readItems(categoryList);

        if(Optional.ofNullable(itemNameTextField).isPresent()) {
            itemList = itemList.stream().filter(c -> c.getName().contains(itemNameTextField)).toList();
        }
        if(Optional.ofNullable(categoryChoiceBox).isPresent()) {
            itemList = itemList.stream().filter(c -> c.getCategory().getName().contains(categoryChoiceBox)).toList();
        }

        if(itemList.isEmpty()) {
            Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Item search results", "Search criteria results in no items", "No items contains name '" + itemNameTextField + "' and category '" + categoryChoiceBox + "'.");
            alert.showAndWait();
        }
        return itemList;
    }
    public static List<Factory> searchFactories(String factoryNameTextField, String cityChoiceBox) {
        List<Category> categoryList = FileUtils.readCategories();
        List<Item> itemList = FileUtils.readItems(categoryList);
        List<Address> addressList = FileUtils.readAddresses();
        List<Factory> factoryList = FileUtils.readFactories(itemList, addressList);

        if(Optional.ofNullable(factoryNameTextField).isPresent()) {
            factoryList = factoryList.stream().filter(c -> c.getName().contains(factoryNameTextField)).toList();
        }
        if(Optional.ofNullable(cityChoiceBox).isPresent()) {
            factoryList = factoryList.stream().filter(c -> c.getAddress().getCity().contains(cityChoiceBox)).toList();
        }
        return factoryList;
    }
    public static List<Store> searchStore(String storeNameTextField, String storeWebAddressTextField, String storeWebAddressTableColumn) {
        List<Category> categoryList = FileUtils.readCategories();
        List<Item> itemList = FileUtils.readItems(categoryList);
        List<Store> storeList = FileUtils.readStores(itemList);

        if(Optional.ofNullable(storeNameTextField).isPresent() && Optional.ofNullable(storeWebAddressTextField).isEmpty()) {
            storeList = storeList.stream().filter(c -> c.getName().contains(storeNameTextField)).toList();
        }
        else if(Optional.ofNullable(storeWebAddressTextField).isPresent() && Optional.ofNullable(storeNameTextField).isEmpty()) {
            storeList = storeList.stream().filter(c -> c.getName().contains(storeWebAddressTextField)).toList();
        }
        else if(Optional.ofNullable(storeNameTextField).isPresent() && Optional.ofNullable(storeWebAddressTableColumn).isPresent()) {
            storeList = storeList.stream().filter(c -> c.getName().contains(storeNameTextField) && c.getWebAddress().contains(storeWebAddressTextField)).toList();
        }
        return storeList;
    }
    public static void addCategory(String categoryNameTextField, String categoryDescriptionTextField) {
        List<Category> categoryList = FileUtils.readCategories();

        Category category = new Category(categoryNameTextField, FileUtils.generateNewCategoryId(), categoryDescriptionTextField);
        categoryList.add(category);

        try {
            FileUtils.saveCategories(categoryList);
            Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Category added successfully!", "Category was added to file!", "Category '" + category.getName() +"' was successfully added.");
            alert.showAndWait();
        } catch (FileSaveException e) {
            Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Error while saving data!", "Categories weren't saved to file!", e.getMessage());
            alert.showAndWait();

            throw new RuntimeException(e);
        }
    }
    public static void addItem(String itemNameTextField, String categoryChoiceBox, String itemWidthTextField, String itemHeightTextField, String itemLengthTextField, String itemProductionCostTextField, String itemSellingPriceTextField) {
        List<Category> categoryList = FileUtils.readCategories();
        List<Item> itemList = FileUtils.readItems(categoryList);

        Category itemCategory = null;
        for(Category category : categoryList) {
            if(category.getName().equals(categoryChoiceBox)) {
                itemCategory = category;
            }
        }
        BigDecimal itemWidth = BigDecimal.valueOf(Integer.parseInt(itemWidthTextField));
        BigDecimal itemHeight = BigDecimal.valueOf(Integer.parseInt(itemHeightTextField));
        BigDecimal itemLength = BigDecimal.valueOf(Integer.parseInt(itemLengthTextField));
        BigDecimal itemProductionCost = BigDecimal.valueOf(Integer.parseInt(itemProductionCostTextField));
        BigDecimal itemSellingPrice = BigDecimal.valueOf(Integer.parseInt(itemSellingPriceTextField));

        Item item = new Item(itemNameTextField, FileUtils.generateNewItemId(), itemCategory, itemWidth, itemHeight, itemLength, itemProductionCost, itemSellingPrice, new Discount(BigDecimal.valueOf(0)));
        itemList.add(item);

        try {
            FileUtils.saveItems(itemList);
            Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Item added successfully!", "Item was added to file!", "Items with name '" + itemNameTextField + "' and category '" + categoryChoiceBox + "' was successfully added.");
            alert.showAndWait();

        } catch (FileSaveException e) {
            Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Error while saving data!", "Items weren't saved to file!", e.getMessage());
            alert.showAndWait();

            throw new RuntimeException(e);
        }
    }
    public static void addFactory(String factoryNameTextField, String factoryChoiceBox, ObservableList<String> selectedItems) {
        List<Category> categoryList = FileUtils.readCategories();
        List<Item> itemList = FileUtils.readItems(categoryList);
        List<Long> itemIds = new ArrayList<>();
        List<Address> addressList = FileUtils.readAddresses();
        List<Factory> factoryList = FileUtils.readFactories(itemList, addressList);

        Address factoryAddress = null;
        for(Address address : addressList) {
            if(address.getCity().equals(factoryChoiceBox)) {
                factoryAddress = address;
            }
        }

        for(String string : selectedItems) {
            for(Item item : itemList) {
                if(string.equals(item.getName())) {
                    itemIds.add(item.getId());
                }
            }
        }

        Set<Item> setFactoryItems = new HashSet<>();
        for(Long id : itemIds) {
            for(Item item : itemList) {
                if(item.getId().equals(id)) {
                    setFactoryItems.add(item);
                }
            }
        }

        Factory factory = new Factory(factoryNameTextField, FileUtils.generateNewFactoryId(), factoryAddress, setFactoryItems);
        factoryList.add(factory);

        try {
            FileUtils.saveFactories(factoryList);
            Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Factory added successfully!", "Factory was added to file!", "Factory '" + factory.getName() +  "' was successfully added.");
            alert.showAndWait();
        } catch (FileSaveException e) {
            Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Error while saving data!", "Factories weren't saved to file!", e.getMessage());
            alert.showAndWait();

            throw new RuntimeException(e);
        }
    }
    public static void addStore(String storeNameTextField, String storeWebAddressTextField, ObservableList<String> selectedItems) {
        List<Category> categoryList = FileUtils.readCategories();
        List<Item> itemList = FileUtils.readItems(categoryList);
        List<Long> itemIds = new ArrayList<>();
        List<Store> storeList = FileUtils.readStores(itemList);

        for(String string : selectedItems) {
            for(Item item : itemList) {
                if(string.equals(item.getName())) {
                    itemIds.add(item.getId());
                }
            }
        }

        Set<Item> setStoreItems = new HashSet<>();
        for(Long id : itemIds) {
            for(Item item : itemList) {
                if(item.getId().equals(id)) {
                    setStoreItems.add(item);
                }
            }
        }

        Store store = new Store(storeNameTextField, FileUtils.generateNewStoreId(), storeWebAddressTextField, setStoreItems);
        storeList.add(store);

        try {
            FileUtils.saveStores(storeList);
            Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Store added successfully!", "Store was added to file!", "Store '" + store.getName() +  "' was successfully added.");
            alert.showAndWait();
        } catch (FileSaveException e) {
            Alert alert = FxDialogsUtils.getAlert(Alert.AlertType.INFORMATION, "Error while saving data!", "Stores weren't saved to file!", e.getMessage());
            alert.showAndWait();

            throw new RuntimeException(e);
        }
    }
}
