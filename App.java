import java.text.DecimalFormat;

public class App {
    public static void main(String[] args) throws Exception {
        DecimalFormat df = new DecimalFormat("#,###.00");
        MealSet[] mealset = new MealSet[4];
        UserInput userIn = new UserInput();
        boolean addOrderYORN = true;
        int orderNum = 0;
        double totalAmount = 0;

        while(addOrderYORN) {
            addOrderYORN = userIn.addOrder(4);
            if(!addOrderYORN) break;

            System.out.println("\n\t\t   Order Number #"+(orderNum+1));
            mealset[orderNum] = new MealSet();
            mealset[orderNum].addMeals();
            mealset[orderNum].addDrinks();
            mealset[orderNum].addSides();
            System.out.println("Total: "+mealset[orderNum].getTotalAmount());
            totalAmount += mealset[orderNum].getTotalAmount(); 

            orderNum++;
        }

        System.out.println("\n\n\t\t     Ordered:");
        for(int i=0 ; i<orderNum ;i++){
            mealset[i].displayOrder();
        }
        System.out.println("Total Amount: "+ df.format(totalAmount));

    }
}
