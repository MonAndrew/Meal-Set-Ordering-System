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
        UserInput userIn = new UserInput();

        boolean addOrderYORN = true;
        int orderNum = 0;
        double totalAmount = 0;

        //GUI gui = new GUI(800, 1200);
        //gui.setupGUI();
        
        while(addOrderYORN) {
            addOrderYORN = userIn.checkYesOrNo("Add Order (Y/N): ","\t\t **Out of Selection!**");

            showOrderedMealSet(mealSetList);

            if(!addOrderYORN) break;

            System.out.println("\n\t\t   Order Number #"+(orderNum));
            
            double tempTotal = addNewOrder(orderNum);

            if(userIn.checkYesOrNo("Confirm Add Order (Y/N): ","INVALID CHOICE!")){
                
                totalAmount += tempTotal;
                passCurrentOrderToMealSet(orderNum);
            }
            else{
                removeMealSet(orderNum);
                orderNum--;
            }

            orderNum++;
        }

        //showOrderedMealSet(mealSetList);

        System.out.println("Total Amount: "+ df.format(totalAmount));
            

        //System.out.println("Hello");
    }

    public double addNewOrder(int orderNum){
        addMealSet(new MealSet());

            MealSet currentOrder = this.mealSetList.get(orderNum);

            currentOrder.addMeals();
            currentOrder.addDrinks();
            currentOrder.addSides();
            currentOrder.addExtras();


            System.out.println("Total: "+currentOrder.getTotalAmount());

            return currentOrder.getTotalAmount();
    }

    public void addMealSet(MealSet mealset){
        this.mealSetList.add(mealset);
    }

    public void removeMealSet(int orderNum){
        this.mealSetList.remove(orderNum);
    }

    public void passCurrentOrderToMealSet(int orderNum){
        MealSet currentOrder = this.mealSetList.get(orderNum);

        currentOrder.passOrderedMealSetToStoreOrder(true);
    }

    public void showOrderedMealSet(List<MealSet> mealSet){
        System.out.println("ORDER/S: \n");

        int orderNum = 0;
        for(MealSet m : mealSet){
            System.out.print("#"+ orderNum + " - ");
            m.displayOrder();
            System.out.println();

            orderNum++;
        }
    }

}
