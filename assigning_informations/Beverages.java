package assigning_informations;

//import UserInput;
import templates.Drinks;

public class Beverages {
    public Drinks drinksArray[] = new Drinks[4];
    //public UserInput userIn = new UserInput();
    private int numberLimit;

    public Beverages(){
        this.numberLimit = 0;

        drinksArray[0] = new Drinks("None",0.00);
        drinksArray[1] = new Drinks("Coca-Cola",30.00);
        drinksArray[2] = new Drinks("Apple Juice",15.00);
        drinksArray[3] = new Drinks("Pineapple Juice",12.00);
    }

    public Beverages(int numberLimit){ 
        this.numberLimit = numberLimit;
    }

    public Drinks returnDrinks(int num){ 
        return drinksArray[num];
    }

    public void displayDrinks(){
        int count = 0;
        for(Drinks s : drinksArray){
            System.out.print("["+count+"] ");
            System.out.println(s.getName() + " - Php "+s.getPrice());
            count++;
        }
    }

    public int getNumberLimit(){ return this.numberLimit;}
    
}
