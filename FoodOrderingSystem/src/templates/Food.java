package templates;

public class Food extends MealTemplate{

    private String foodName = "";
    private double foodPrice = 0;

    public Food(){
        this.foodName = "N/A";
        this.foodPrice = 0.0;
    }

    public Food(String name, double price){
        this.foodName = name;
        this.foodPrice = price;
    }

    @Override
    public String getName(){return this.foodName;}
    @Override
    public double getPrice(){return this.foodPrice;}
}

