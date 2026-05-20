import graphical_user_interface.GUI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderLogic {
    DecimalFormat df = new DecimalFormat("#,###.00");
    
    private List<MealSet> mealSetList;

    UserInput userIn = new UserInput();

    public OrderLogic() {
        this.mealSetList = new ArrayList<>();
    }

    public void launch(){

        boolean addOrderYORN = true;
        //int orderNum = 0;
        double totalAmount = 0;

        GUI gui = new GUI(800, 1200);
        gui.setupGUI();
        
        while(addOrderYORN) {
            //addOrderYORN = userIn.checkYesOrNo("Add Order (Y/N): ","\t\t **Out of Selection!**");

            //showOrderedMealSetWithOptionToRemoval();

            //if(!addOrderYORN) break;

            switch(Character.toLowerCase(userIn.checkUserInputForOption("OPTION:\n(A)Add\t (R)Remove\t (S)Show Order\t (C)Confirm\t", " INVALID!"))){
                case 'a' -> {
                    System.out.println("\n\t\t   NEW ORDER:");
                
                    double tempTotal = addNewOrder();

                    if(userIn.checkYesOrNo("Confirm Add Order (Y/N): ","INVALID CHOICE!")){
                        
                        totalAmount += tempTotal;
                        passCurrentOrderToMealSet(mealSetList.size() - 1);
                    }
                    else{
                        totalAmount -= removeMealSet(mealSetList.size() - 1);
                        //orderNum--;
                    }
                }
                case 'r' -> {
                    showOrderedMealSetWithOptionToRemoval();
                }

                case 's' -> {
                    showOrderedMealSet(mealSetList);
                }
                case 'c' -> {

                    //change this to confirm order
                    //TODO fix Exit
                    if(mealSetList.isEmpty()){
                        if(!userIn.checkYesOrNo("Order is Empty, Confirm Order? (Y/N): ","\t\t INVALID!")) break;
                        
                    }
                    else{
                    //addOrderYORN = userIn.checkYesOrNo("Confirm Order (Y/N): ","\t\t INVALID!");
                        if(userIn.checkYesOrNo("Confirm Order (Y/N): ","\t\t INVALID!")) addOrderYORN = false;
                    }
                }

                default -> {System.out.println("INVALID!");}
            }
        }

        //showOrderedMealSet(mealSetList);

        System.out.println("Total Amount: "+ df.format(totalAmount));
            

        //System.out.println("Hello");
    }

    public double addNewOrder(){
        addMealSet(new MealSet());

            MealSet currentOrder = this.mealSetList.get(mealSetList.size() - 1);

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

    public double  removeMealSet(int orderNum){
        double deduction = this.mealSetList.get(orderNum).getTotalAmount();
        this.mealSetList.remove(orderNum);
        return deduction;
    }

    public void passCurrentOrderToMealSet(int orderNum){
        MealSet currentOrder = this.mealSetList.get(orderNum);

        currentOrder.passOrderedMealSetToStoreOrder(true);
    }

    public void selectOption(char opt){
        switch(Character.toLowerCase(opt)){
            case 'a' -> {
                System.out.println("\n\t\t   NEW ORDER:");
            
                double tempTotal = addNewOrder();

                if(userIn.checkYesOrNo("Confirm Add Order (Y/N): ","INVALID CHOICE!")){
                    
                    //totalAmount += tempTotal;
                    passCurrentOrderToMealSet(mealSetList.size() - 1);
                }
                else{
                    removeMealSet(mealSetList.size() - 1);
                    //orderNum--;
                }
            }
            case 'r' -> {
                showOrderedMealSetWithOptionToRemoval();
            }

            case 's' -> {
                showOrderedMealSet(mealSetList);
            }
            case 'c' -> {}

            default -> {System.out.println("INVALID!");}
        }
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

    public void showOrderedMealSetWithOptionToRemoval(){
        if(!mealSetList.isEmpty()){

                showOrderedMealSet(mealSetList);

                if(userIn.checkYesOrNo("Would You like to remove an order? (Y/N)", "INVALID CHOICE!")){
                    this.mealSetList.remove(userIn.checkUserInputLessThanLimitOrZero(mealSetList.size(), "Remove Order Number: ", "\\t\\t **Out of Selection!**"));
                    showOrderedMealSet(mealSetList);
                }

            }
    }

}
