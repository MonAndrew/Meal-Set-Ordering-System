package assigning_informations;

import templates.Sides;

public class SidesAssign {
    public Sides sidesArray[] = new Sides[4];
    //public UserInput userIn = new UserInput();
    private int numberLimit;

    public SidesAssign(){
        this.numberLimit = 0;

        sidesArray[0] = new Sides("None",0.00);
        sidesArray[1] = new Sides("Large Fries",30.00);
        sidesArray[2] = new Sides("Regular Fries",20.00);
        sidesArray[3] = new Sides("small Fries",10.00);
    }

    public SidesAssign(int numberLimit){ 
        this.numberLimit = numberLimit;
    }

    public Sides returnSides(int num){ 
        return sidesArray[num];
    }

    public void displaySides(){
        int count = 0;
        for(Sides s : sidesArray){
            System.out.print("["+count+"] ");
            System.out.println(s.getName() + " - Php "+s.getPrice());
            count++;
        }
    }

    public int getNumberLimit(){ return this.numberLimit;}

}
