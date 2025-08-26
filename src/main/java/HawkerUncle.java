import java.util.Scanner;

public class HawkerUncle {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("  Hello! I'm HawkerUncle");
        System.out.println("  What can I do for you?");

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while(true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("  Bye. Hope to see you again soon!");
                break;
            }

            try {
                if (input.equalsIgnoreCase("list")) {
                    System.out.println("  Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; ++i) {
                        System.out.println("  " + (i + 1) + ". " + tasks[i]);
                    }
                } else if (input.toLowerCase().startsWith("mark ")) {
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (idx  < 0 || idx >= taskCount) throw new IndexOutOfBoundsException();
                    tasks[idx].isDone = true;
                    System.out.println("  Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[idx]);
                } else if (input.toLowerCase().startsWith("unmark ")) {
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (idx < 0 || idx >= taskCount) throw new IndexOutOfBoundsException();
                    tasks[idx].isDone = false;
                    System.out.println("  OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[idx]);
                } else if (input.toLowerCase().startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    if (description.isBlank()) throw new IllegalArgumentException("The description of a todo cannot be empty.");
                    tasks[taskCount] = new ToDo(description);
                    taskCount++;
                    printTaskAdded(tasks[taskCount - 1].toString(), taskCount);
                } else if (input.toLowerCase().startsWith("deadline ")) {
                    if (!input.contains(" /by ")) throw new IllegalArgumentException("Deadline must contain /by");
                    String[] sections = input.substring(9).split(" /by", 2);
                    String description = sections[0].trim();
                    String by = sections[1].trim();
                    if (description.isBlank() || by.isBlank()) throw new IllegalArgumentException("Deadline description and /by cannot be empty");
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    printTaskAdded(tasks[taskCount - 1].toString(), taskCount);
                } else if (input.toLowerCase().startsWith("event ")) {
                    if (!input.contains(" /from ") || !input.contains(" /to ")) throw new IllegalArgumentException("Event must contain /from and /to");
                    String[] sections = input.substring(6).split(" /from | /to ", 3);
                    String description = sections[0].trim();
                    String from = sections[1].trim();
                    String to = sections[2].trim();
                    if (description.isBlank() || from.isBlank() || to.isBlank()) throw new IllegalArgumentException("Event description, /from, and /to cannot be empty");
                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;
                    printTaskAdded(tasks[taskCount - 1].toString(), taskCount);
                }
                else{
                    throw new UnsupportedOperationException("I'm sorry, I don't know what that means.");
                }
            } catch (IllegalArgumentException e){
                System.out.println("  OOPS!!! " + e.getMessage());
            } catch (IndexOutOfBoundsException e){
                System.out.println("  OOPS!!! Task number is invalid");
            } catch (Exception e){
                System.out.println("  OOPS!!! " + e.getMessage());
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