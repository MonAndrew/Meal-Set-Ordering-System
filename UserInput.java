import java.util.Scanner;

public class UserInput {
    public Scanner scan = new Scanner(System.in);

    public int userInput(int numberLimit){
        int num = 0;
        boolean isValid = false;

        while(!isValid){
            try {
                System.err.print("Enter Selection: ");
                num = scan.nextInt();
                if(num < numberLimit) return num;
                else System.out.println("\t\t **Out of Selection!**");
                
            } catch (Exception e) {
                System.out.println("INVALID!");
            }
        }

        return num;
    }

    public boolean addOrder(int numberLimit){
        char num = ' ';
        boolean isValid = false;

        while(!isValid){
            try {
                System.out.print("Add Order (Y/N): ");
                num = scan.next().charAt(0);

                switch(Character.toLowerCase(num)){
                    case 'y': return true;
                    case 'n': return false;
                    default: 
                    System.out.println("\t\t **Out of Selection!**"); 
                    isValid = false;
                }
                
            } catch (Exception e) {
                System.out.println("INVALID!");
            }
        }
        return false;
    }
}
