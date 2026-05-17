package store_order;

import java.util.ArrayList;
import java.util.List;

public class StoreMeals {

    private List<StoreMeals> storeOrderedMealsList = new ArrayList<>();

    private String food;
    private double price;

    public StoreMeals(String food, double price) {
        this.food = food;
        this.price = price;
    }

    public void addStoreMeals(StoreMeals storeMeal){
        this.storeOrderedMealsList.add(storeMeal);
    }

    //TODO add method that store all meals menu and checker for meal dupes to increase quantity
    
}
