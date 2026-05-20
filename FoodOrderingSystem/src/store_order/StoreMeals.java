package store_order;

import java.util.ArrayList;
import java.util.List;

public class StoreMeals {

    private List<StoreMeals> storeOrderedMealsList = new ArrayList<>();

    private String foodName;
    private double foodPrice;
    private int quantity;

    public StoreMeals(String foodName, double foodPrice) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.quantity += 1;
    }  

    public void addStoreMeals(StoreMeals storeMeal){
        this.storeOrderedMealsList.add(storeMeal);
    }

    public void checkStoreMeal(List<StoreMeals> list, StoreMeals store){
        for(StoreMeals l : list){
            if(l.foodName.equals(store.foodName)){
                
            }
        }
    }

    //TODO add method that store all meals menu and checker for meal dupes to increase quantity
    
}
