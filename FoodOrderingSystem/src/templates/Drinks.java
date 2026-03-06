package templates;

public class Drinks extends MealTemplate{
    
    private String drinksName = "";
    private double drinksPrice = 0;

    public Drinks(){
        this.drinksName = "N/A";
        this.drinksPrice = 0.0;
    }

    public Drinks(String name, double price){
        this.drinksName = name;
        this.drinksPrice = price;
    }

    @Override
    public String getName(){return this.drinksName;}
    @Override
    public double getPrice(){return this.drinksPrice;}
}
