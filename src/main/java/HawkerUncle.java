import java.util.Scanner;

public class HawkerUncle {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("  Hello! I'm HawkerUncle");
        System.out.println("  What can I do for you?");

        String[] texts = new String[100];
        int textCount = 0;
        boolean[] isMarked = new boolean[100];

        while(true){
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")){
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equalsIgnoreCase("list")){
                for (int i = 0; i < textCount; ++i){
                    String status = isMarked ? "[X]" : "[ ]";
                    System.out.println("  " + (i + 1) + ". " + status + " " + texts[i]);
                }
            }
            else if (input.toLowerCase().startsWith("mark ")){
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                isMarked[idx] = true;
                System.out.println("  Nice! I've marked this task as done:");
                System.out.println("  [X] " + texts[idx]);
            }
            else if (input.toLowerCase().startsWith("unmark ")){
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                isMarked[idx] = false;
                System.out.println("  OK, I've marked this task as not done yet:");
                System.out.println("  [ ] " + texts[idx]);
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