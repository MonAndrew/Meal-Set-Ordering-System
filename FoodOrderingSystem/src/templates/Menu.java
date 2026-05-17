package templates;

public class Menu extends MealTemplate{
    
    private String name = "";
    private double price = 0;

    public Menu(){
        this.name = "N/A";
        this.price = 0.0;
    }

    public Menu(String name, double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName(){return this.name;}
    @Override
    public double getPrice(){return this.price;}
}
