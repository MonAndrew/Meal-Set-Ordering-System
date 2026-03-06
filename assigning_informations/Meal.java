package assigning_informations;

//import UserInput;
import templates.Food;

public class Meal {
    public Food foodArray[] = new Food[4];
    //public UserInput userIn = new UserInput();
    private int numberLimit;

    public Meal(){
        this.numberLimit = 0;

        foodArray[0] = new Food("None",0.00);
        foodArray[1] = new Food("Burger",20.00);
        foodArray[2] = new Food("Cheese Burger",30.00);
        foodArray[3] = new Food("Double Patty Burger",40.00);
    }

    public Meal(int numberLimit){ 
        this.numberLimit = numberLimit;
    }

    public Food returnFood(int num){ 
        return foodArray[num];
    }

    public void displayFoods(){
        int count = 0;
        for(Food s : foodArray){
            System.out.print("["+count+"] ");
            System.out.println(s.getName() + " - Php "+s.getPrice());
            count++;
        }
    }

    public int getNumberLimit(){ return this.numberLimit;}
}
