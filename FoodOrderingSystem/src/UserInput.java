
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    public Scanner scan = new Scanner(System.in);

    public int checkUserInputLessThanLimitOrZero(int numberLimit, String msg1, String msg2){
        int num = 0;
        boolean isValid = false;

        while(!isValid){
            try {
                System.out.print(msg1);
                num = scan.nextInt();
                if(num < numberLimit && num >= 0) return num;
                else System.out.println("\t\t **Out of Selection!**");
                
            } catch (InputMismatchException e) {
                System.out.println(msg2);
                scan.next();
            }
        }

        return num;
    }

    public boolean checkYesOrNo(String msg1, String msg2){
        char confirmation;
        boolean isValid = false;

        while(!isValid){
            try {
                System.out.print(msg1);
                confirmation = scan.next().charAt(0);

                switch(Character.toLowerCase(confirmation)){
                    case 'y'-> {return true;}
                    case 'n'-> {return false;}
                    default ->{
                    System.out.println(msg2); 
                    isValid = false;}
                }
                
            } catch (Exception e) {
                System.out.println("INVALID!");
                scan.next();
            }
        }
        return false;
    }

    public char checkUserInputForOption(String msg1, String msg2){
        char character;
        boolean isValid = false;

        while(!isValid){
            try {
                System.out.print(msg1);
                character = scan.next().charAt(0);

                switch(Character.toLowerCase(character)){
                    case 'a','r','s','c' -> {return character;}

                    default -> {
                    System.out.println(msg2); 
                    isValid = false;}
                }
                
            } catch (Exception e) {
                System.out.println("INVALID!");
                scan.next();
            }
        }

        return 's';
    }
}
