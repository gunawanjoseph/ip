import java.util.Scanner;

public class HawkerUncle {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("  Hello! I'm HawkerUncle");
        System.out.println("  What can I do for you?");

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while(true){
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")){
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equalsIgnoreCase("list")){
                for (int i = 0; i < taskCount; ++i){
                    System.out.println("  " + (i + 1) + ". " + tasks[i]);
                }
            }
            else if (input.toLowerCase().startsWith("mark ")){
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[idx].isDone = true;
                System.out.println("  Nice! I've marked this task as done:");
                System.out.println("  " + tasks[idx]);
            }
            else if (input.toLowerCase().startsWith("unmark ")){
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[idx].isDone = false;
                System.out.println("  OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[idx]);
            }
            else{
                System.out.println("  added: " + input);
                tasks[taskCount] = new Task(input);
                taskCount++;
            }
        }

        scanner.close();
    }
}