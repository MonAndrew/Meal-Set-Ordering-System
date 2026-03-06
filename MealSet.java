import assigning_informations.Beverages;
import assigning_informations.Meal;
import assigning_informations.SidesAssign;

public class MealSet{
    Beverages  beverages = new Beverages();
    Meal meal = new Meal();
    SidesAssign sides = new SidesAssign();
    UserInput userIn = new UserInput();

    private double totalAmount;
    String foodOrdered = "";
    String drinkOrdered = "";
    String sideOrdered = "";

    double foodOrderedPrice = 0;
    double drinkOrderedPrice = 0;
    double sideOrderedPrice = 0;

    public void addMeals(){
        meal.displayFoods();
        int user = userIn.userInput(4);
        this.totalAmount += meal.returnFood(user).getPrice();
        this.foodOrderedPrice = meal.returnFood(user).getPrice();
        this.foodOrdered = meal.returnFood(user).getName();
    }

    public void addDrinks(){
        beverages.displayDrinks();
        int user = userIn.userInput(4);
        this.totalAmount += beverages.returnDrinks(user).getPrice();
        this.drinkOrderedPrice = beverages.returnDrinks(user).getPrice();
        this.drinkOrdered = beverages.returnDrinks(user).getName();
    }

    public void addSides(){
        sides.displaySides();
        int user = userIn.userInput(4);
        this.totalAmount += sides.returnSides(user).getPrice();
        this.sideOrderedPrice = sides.returnSides(user).getPrice();
        this.sideOrdered = sides.returnSides(user).getName();
    }

    public void displayOrder(){
        System.out.println(foodOrdered + "("+foodOrderedPrice+") + "+ drinkOrdered + "("+drinkOrderedPrice+") + " + sideOrdered + "("+sideOrderedPrice+") = Php" + getTotalAmount());
    }
    
    public double getTotalAmount(){return this.totalAmount;}
}