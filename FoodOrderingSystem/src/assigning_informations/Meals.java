package assigning_informations;

import java.util.ArrayList;
import java.util.List;
import templates.Menu;

public class Meals {
    
    private List<Menu> foodList;
    private int numberLimit;

    public Meals(){
        this.numberLimit = 0;

        this.foodList = new ArrayList<>();
        mealInitialize();
    }

    private void mealInitialize(){
        addMeal(new Menu("None",0.00));
        addMeal(new Menu("Burger", 20.00));
        addMeal(new Menu("Cheese Burger", 30.00));
        addMeal(new Menu("Double Patty Burger", 50.00));
        addMeal(new Menu("Double Cheese Burger", 40.00));
        addMeal(new Menu("Double Combo Burger", 70.00));
        addMeal(new Menu("Buffalo Wings Solo w/ Rice", 120.00));
        addMeal(new Menu("Buffalo Wings Platter", 220.00));

        this.numberLimit = foodList.size();
    }

    public void addMeal(Menu food){
        this.foodList.add(food);
    }

    public Menu getFood(int num){ 
        return foodList.get(num);
    }

    public void displayFoods(){
        int count = 0;
        for(Menu s : foodList){
            System.out.print("["+count+"] ");
            System.out.println(s.getName() + " - Php "+s.getPrice());
            count++;
        }
    }

    public int getNumberLimit(){ return this.numberLimit;}
}
