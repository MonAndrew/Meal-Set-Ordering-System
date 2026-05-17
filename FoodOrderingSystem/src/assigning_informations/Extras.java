package assigning_informations;

import java.util.ArrayList;
import java.util.List;
import templates.Menu;

public class Extras {

    private List<Menu> extraList;
    private int numberLimit;

    public Extras(){
        this.numberLimit = 0;
        this.extraList = new ArrayList<>();
        extrasInitialize();
    }

    private void extrasInitialize(){

        addExtra(new Menu("None",0.00));
        addExtra(new Menu("Tomato Ketchup",10.00));
        addExtra(new Menu("Mustard", 10.00));
        addExtra(new Menu("Ranch", 10.00));
        addExtra(new Menu("Rice", 10.00));

        this.numberLimit = extraList.size();
    }

    public void addExtra(Menu drink){
        this.extraList.add(drink);
    }

    public Menu getExtra(int num){
        return extraList.get(num);
    }

    public void displayExtras(){
        int count = 0;
        for(Menu s : extraList){
            System.out.print("["+count+"] ");
            System.out.println(s.getName() + " - Php "+s.getPrice());
            count++;
        }
    }

    public int getNumberLimit(){ 
        return this.numberLimit;
    }
}

