import java.util.Scanner;

public class HawkerUncle {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm HawkerUncle");
        System.out.println("What can I do for you?");

        while(true){
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")){
                System.out.println(" Bye. Hope to se you again soon!");
                break;
            }

            System.out.println("  " + input);
        }

        scanner.close();
    }
}