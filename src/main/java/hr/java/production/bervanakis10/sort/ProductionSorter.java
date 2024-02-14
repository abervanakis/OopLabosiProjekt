package hr.java.production.bervanakis10.sort;

import hr.java.production.bervanakis10.model.Item;

import java.util.Comparator;

public class ProductionSorter implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        /*if(o1.getSellingPrice().compareTo(o2.getSellingPrice()) > 0) {
            return 1;
        }
        else if(o1.getSellingPrice().compareTo(o2.getSellingPrice()) < 0) {
            return -1;
        }
        else if(o1.getSellingPrice().compareTo(o2.getSellingPrice()) == 0){
            return 0;
        }*/
        return o1.getSellingPrice().compareTo(o2.getSellingPrice());
    }
}
