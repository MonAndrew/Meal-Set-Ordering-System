import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderLogic {
    
    private List<MealSet> mealSetList;

    public OrderLogic() {
        this.mealSetList = new ArrayList<>();
    }

    public void launch(){
        
        DecimalFormat df = new DecimalFormat("#,###.00");
        MealSet[] mealset = new MealSet[4];
        UserInput userIn = new UserInput();

        boolean addOrderYORN = true;
        int orderNum = 0;
        double totalAmount = 0;

        //GUI gui = new GUI(800, 1200);
        //gui.setupGUI();
        
        while(addOrderYORN) {
            addOrderYORN = userIn.checkYesOrNo();
            if(!addOrderYORN) break;

            System.out.println("\n\t\t   Order Number #"+(orderNum+1));
            mealset[orderNum] = new MealSet();
            mealset[orderNum].addMeals();
            mealset[orderNum].addDrinks();
            mealset[orderNum].addSides();

            //arrayList
            //addMealSet(new MealSet());


            System.out.println("Total: "+mealset[orderNum].getTotalAmount());
            totalAmount += mealset[orderNum].getTotalAmount(); 

            orderNum++;
        }

        System.out.println("\n\nOrdered:");
        for(int i=0 ; i<orderNum ;i++){
            mealset[i].displayOrder();
        }
        System.out.println("Total Amount: "+ df.format(totalAmount));
            

        //System.out.println("Hello");
    }

    public void addMealSet(MealSet mealset){
        this.mealSetList.add(mealset);
    }

}
