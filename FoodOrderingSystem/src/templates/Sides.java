package templates;

public class Sides extends MealTemplate {
    private String sidesName = "";
    private double sidesPrice = 0;

    public Sides(){
        this.sidesName = "N/A";
        this.sidesPrice = 0.0;
    }

    public Sides(String name, double price){
        this.sidesName = name;
        this.sidesPrice = price;
    }

    @Override
    public String getName(){return this.sidesName;}
    @Override
    public double getPrice(){return this.sidesPrice;}
}
