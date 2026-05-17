
import assigning_informations.Beverages;
import assigning_informations.Extras;
import assigning_informations.Meals;
import assigning_informations.Sides;

public class MealSet{
    Beverages  beverages = new Beverages();
    Meals meal = new Meals();
    Sides sides = new Sides();
    Extras extras = new Extras();

    UserInput userIn = new UserInput();

    private double totalAmount;
    private String foodOrdered = "";
    private String drinkOrdered = "";
    private String sideOrdered = "";

    private double foodOrderedPrice = 0;
    private double drinkOrderedPrice = 0;
    private double sideOrderedPrice = 0;

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

        this.sideOrderedPrice = extras.getExtra(user).getPrice();
        this.sideOrdered = extras.getExtra(user).getName();

        this.totalAmount += extras.getExtra(user).getPrice();
    }

    public boolean isFoodOrderedNull(){
        return !this.foodOrdered.equals("None");
    }

    public void displayOrder(){

        System.out.println(isFoodOrderedNull());

        System.out.println(this.foodOrdered + "("+this.foodOrderedPrice+") + "+ 
        this.drinkOrdered + "("+this.drinkOrderedPrice+") + " + 
        this.sideOrdered + "("+this.sideOrderedPrice+") = Php " + getTotalAmount());
    }
    
    public double getTotalAmount(){return this.totalAmount;}
}