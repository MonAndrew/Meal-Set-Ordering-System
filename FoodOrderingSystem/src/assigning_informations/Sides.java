package assigning_informations;

import java.util.ArrayList;
import java.util.List;
import templates.Menu;

public class Sides {


    private List<Menu> sidesList;

    private int numberLimit;

    public Sides(){

        this.numberLimit = 0;
        this.sidesList = new ArrayList<>();
        sidesInitialize();

    }

    private void sidesInitialize(){

        addSides(new Menu("None",0.00));
        addSides(new Menu("Small Fries",20.00));
        addSides(new Menu("Regular Fries",30.00));
        addSides(new Menu("Large Fries",40.00));
        addSides(new Menu("Sundae Ice Cream",60.00));
        //addSides(new Sides("",num));


        this.numberLimit = sidesList.size();
}


    public void addSides(Menu sides){

        this.sidesList.add(sides);

    }


    public Menu getSides(int num){
        return sidesList.get(num);
    }

    public void displaySides(){

        int count = 0;
        for(Menu s : sidesList){
            System.out.print("["+count+"] ");
            System.out.println(s.getName() + " - Php "+s.getPrice());
            count++;

        }

    }

    public int getNumberLimit(){ 
        
        return this.numberLimit;
    }

}
