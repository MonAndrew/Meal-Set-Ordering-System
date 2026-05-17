package assigning_informations;

//import UserInput;
import java.util.ArrayList;
import java.util.List;
import templates.Menu;

public class Beverages {

    private List<Menu> drinksList;

    private int numberLimit;

    public Beverages(){
        this.numberLimit = 0;
        this.drinksList = new ArrayList<>();
        beveragesInitialize();
        
    }

    private void beveragesInitialize(){

        addBeverage(new Menu("None",0.00));
        addBeverage(new Menu("Coca-Cola",30.00));
        addBeverage(new Menu("Coke Float", 50.00));
        addBeverage(new Menu("Lemon Juice", 20.00));
        addBeverage(new Menu("Apple Juice", 20.00));
        addBeverage(new Menu("PineApple Juice", 20.00));
        addBeverage(new Menu("Coffee", 30.00));
        addBeverage(new Menu("Latte", 40.00));

        this.numberLimit = drinksList.size();
    }

    public void addBeverage(Menu drink){
        this.drinksList.add(drink);
    }

    public Menu getDrinks(int num){ 
        return drinksList.get(num);
    }

    public void displayDrinks(){
        int count = 0;
        for(Menu s : drinksList){
            System.out.print("["+count+"] ");
            System.out.println(s.getName() + " - Php "+s.getPrice());
            count++;
        }
    }

    public int getNumberLimit(){ return this.numberLimit;}
    
}
