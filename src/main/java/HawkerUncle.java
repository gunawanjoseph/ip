import java.util.Scanner;

public class HawkerUncle {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("  Hello! I'm HawkerUncle");
        System.out.println("  What can I do for you?");

        String[] texts = new String[100];
        int textCount = 0;

        while(true){
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")){
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equalsIgnoreCase("list")){
                for (int i = 0; i < textCount; ++i){
                    System.out.println("  " + (i + 1) + ". " + texts[i]);
                }
            }
            else{
                System.out.println("  added: " + input);
                texts[textCount] = input;
                textCount++;
            }
        }

        scanner.close();
    }
}