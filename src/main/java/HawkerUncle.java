import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class HawkerUncle {
    private static final String FILE_PATH = "./data/HawkerUncle.txt";
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("  Hello! I'm HawkerUncle");
        System.out.println("  What can I do for you?");

        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage(FILE_PATH);

        try {
            tasks = storage.load();
        } catch (IOException e) {
            tasks = new ArrayList<>();
        }

        while(true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("  Bye. Hope to see you again soon!");
                break;
            }

            try {
                if (input.equalsIgnoreCase("list")) {
                    System.out.println("  Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); ++i) {
                        System.out.println("  " + (i + 1) + ". " + tasks.get(i));
                    }
                } else if (input.toLowerCase().startsWith("mark")) {
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (idx  < 0 || idx >= tasks.size()) throw new IndexOutOfBoundsException();
                    tasks.get(idx).isDone = true;
                    System.out.println("  Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(idx));
                } else if (input.toLowerCase().startsWith("unmark")) {
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (idx < 0 || idx >= tasks.size()) throw new IndexOutOfBoundsException();
                    tasks.get(idx).isDone = false;
                    System.out.println("  OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(idx));
                } else if (input.toLowerCase().startsWith("delete")){
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (idx < 0 || idx >= tasks.size()) throw new IndexOutOfBoundsException();
                    Task removed = tasks.remove(idx);
                    System.out.println("  Noted. I've removed this task:");
                    System.out.println("    " + removed);
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list");
                } else if (input.toLowerCase().startsWith("todo")) {
                    String description = input.substring(4).trim();
                    if (description.isBlank()) throw new IllegalArgumentException("The description of a todo cannot be empty.");
                    tasks.add(new ToDo(description, false));
                    printTaskAdded(tasks.get(tasks.size() - 1).toString(), tasks.size());
                } else if (input.toLowerCase().startsWith("deadline")) {
                    if (!input.contains(" /by")) throw new IllegalArgumentException("Deadline must contain /by");
                    String[] sections = input.substring(8).split(" /by", 2);
                    String description = sections[0].trim();
                    String by = sections[1].trim();
                    if (description.isBlank() || by.isBlank()) throw new IllegalArgumentException("Deadline description and /by cannot be empty");
                    tasks.add(new Deadline(description, by, false));
                    printTaskAdded(tasks.get(tasks.size() - 1).toString(), tasks.size());
                } else if (input.toLowerCase().startsWith("event")) {
                    if (!input.contains(" /from") || !input.contains(" /to"))
                        throw new IllegalArgumentException("Event must contain /from and /to");
                    String[] sections = input.substring(5).split(" /from| /to", 3);
                    String description = sections[0].trim();
                    String from = sections[1].trim();
                    String to = sections[2].trim();
                    if (description.isBlank() || from.isBlank() || to.isBlank())
                        throw new IllegalArgumentException("Event description, /from, and /to cannot be empty");
                    tasks.add(new Event(description, from, to, false));
                    printTaskAdded(tasks.get(tasks.size() - 1).toString(), tasks.size());
                } else{
                    throw new UnsupportedOperationException("I'm sorry, I don't know what that means.");
                }
                storage.save(tasks);
            } catch (IllegalArgumentException e){
                System.out.println("  OOPS!!! " + e.getMessage());
            } catch (IndexOutOfBoundsException e){
                System.out.println("  OOPS!!! Task number is invalid.");
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