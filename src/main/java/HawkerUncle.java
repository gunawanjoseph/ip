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
                System.out.println("  Here are the tasks in your list:");
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
            else if (input.toLowerCase().startsWith("todo ")) {
                String description = input.substring(5).trim();
                tasks[taskCount] = new ToDo(description);
                taskCount++;
                printTaskAdded(tasks[taskCount - 1].toString(), taskCount);
            }
            else if (input.toLowerCase().startsWith("deadline ")){
                String[] sections = input.substring(9).split(" /by");
                String description = sections[0].trim();
                String by = sections[1].trim();
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                printTaskAdded(tasks[taskCount - 1].toString(), taskCount);
            }
            else if (input.toLowerCase().startsWith("event ")){
                String[] sections = input.substring(6).split(" /from | /to ");
                String description = sections[0].trim();
                String from = sections[1].trim();
                String to = sections[2].trim();
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                printTaskAdded(tasks[taskCount - 1].toString(), taskCount);
            }
        }

        scanner.close();
    }
    public static void printTaskAdded(String msg, int taskCount){
        System.out.println("  Got it. I've added this task:");
        System.out.println("    " + msg);
        System.out.println("  Now you have " + taskCount + " tasks in the list.");
    }
}