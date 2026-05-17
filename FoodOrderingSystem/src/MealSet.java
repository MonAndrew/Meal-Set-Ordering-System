
import assigning_informations.Beverages;
import assigning_informations.Extras;
import assigning_informations.Meals;
import assigning_informations.Sides;
import java.text.DecimalFormat;

public class MealSet{
    DecimalFormat dfAmount = new DecimalFormat("#,###.00");

    Beverages  beverages = new Beverages();
    Meals meal = new Meals();
    Sides sides = new Sides();
    Extras extras = new Extras();

    UserInput userIn = new UserInput();

    private double totalAmount;
    public String emptyValue;

    private String foodOrdered;
    private String drinkOrdered;
    private String sideOrdered;
    private String extraOrdered;

    private double foodOrderedPrice;
    private double drinkOrderedPrice;
    private double sideOrderedPrice;
    private double extraOrderedPrice;

    public MealSet(){
        this.emptyValue = "None";

        this.foodOrdered = "";
        this.drinkOrdered = "";
        this.sideOrdered = "";
        this.extraOrdered = "";

        this.foodOrderedPrice = 0;
        this.drinkOrderedPrice = 0;
        this.sideOrderedPrice = 0;
        this.extraOrderedPrice = 0;
    }

    public double getTotalAmount(){return this.totalAmount;}

    public void addMeals(){
        System.out.println("--------------------[ MEAL ]--------------------");
        meal.displayFoods();

        int user = userIn.checkUserInputLessThanLimit(meal.getNumberLimit(),"Enter Selection: ","INVALID!");

        this.foodOrderedPrice = meal.getFood(user).getPrice();
        this.foodOrdered = meal.getFood(user).getName();

        this.totalAmount += meal.getFood(user).getPrice();
    }

    public void addDrinks(){
        System.out.println("-------------------[ DRINKS ]---------------------");
        beverages.displayDrinks();

        int user = userIn.checkUserInputLessThanLimit(beverages.getNumberLimit(),"Enter Selection: ","INVALID!");

        this.drinkOrderedPrice = beverages.getDrinks(user).getPrice();
        this.drinkOrdered = beverages.getDrinks(user).getName();

        this.totalAmount += beverages.getDrinks(user).getPrice();
    }

    public void addSides(){
        System.out.println("-------------------[ SIDES ]---------------------");
        sides.displaySides();

        int user = userIn.checkUserInputLessThanLimit(sides.getNumberLimit(),"Enter Selection: ","INVALID!");

        this.sideOrderedPrice = sides.getSides(user).getPrice();
        this.sideOrdered = sides.getSides(user).getName();

        this.totalAmount += sides.getSides(user).getPrice();
    }

    public void addExtras(){
        System.out.println("-------------------[ EXTRAS ]---------------------");
        extras.displayExtras();

        int user = userIn.checkUserInputLessThanLimit(sides.getNumberLimit(),"Enter Selection: ","INVALID!");

        this.extraOrderedPrice = extras.getExtra(user).getPrice();
        this.extraOrdered = extras.getExtra(user).getName();

        this.totalAmount += extras.getExtra(user).getPrice();
    }

    public boolean isFoodOrderedNull(){
        return !this.foodOrdered.equals(this.emptyValue);
    }

    public boolean isDrinkOrderedNull(){
        return !this.drinkOrdered.equals(this.emptyValue);
    }

    public boolean isSideOrderedNull(){
        return !this.sideOrdered.equals(this.emptyValue);
    }

    public boolean isExtraOrderedNull(){
        return !this.extraOrdered.equals(this.emptyValue);
    }

    public void displayOrder(){

        if(isFoodOrderedNull()){
        System.out.print(this.foodOrdered+" - [ Php "+dfAmount.format(this.foodOrderedPrice)+"] + ");
        }

        if(isDrinkOrderedNull()){
        System.out.print(this.drinkOrdered+" - [ Php "+dfAmount.format(this.drinkOrderedPrice)+"] + ");
        }

        if(isSideOrderedNull()){
        System.out.print(this.sideOrdered+" - [ Php "+dfAmount.format(this.sideOrderedPrice)+"] + ");
        }

        if(isExtraOrderedNull()){
        System.out.print(this.extraOrdered+" - [ Php "+dfAmount.format(this.extraOrderedPrice)+"]: ");
        }

        //System.out.println(this.foodOrdered + " " + this.drinkOrdered + " " + this.sideOrdered + " " + this.extraOrdered);
        System.out.println("Total = Php " + getTotalAmount());
    }

    public void passOrderedMealSetToStoreOrder(boolean isPass){
        if(!isPass) return;

        System.out.println();
    }
    
}