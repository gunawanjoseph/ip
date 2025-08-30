package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.ui.Ui;
import HawkerUncle.task.TaskList;
import HawkerUncle.task.Task;
import HawkerUncle.task.ToDo;
import HawkerUncle.task.Deadline;
import HawkerUncle.task.Event;
import HawkerUncle.parser.Parser;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class AddCommand implements Command {
    private final ArrayList<String> parsedData;

    public AddCommand(ArrayList<String> parsedData) {
        this.parsedData = parsedData;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String taskType = parsedData.get(0);
            if (taskType.equalsIgnoreCase("todo")) {
                String description = parsedData.get(1);
                Task task = new ToDo(description, false);
                tasks.add(task);
                printTaskAdded(task.toString(), tasks.size());
            }else if (taskType.equalsIgnoreCase("deadline")) {
                String description = parsedData.get(1);
                String byStr = parsedData.get(2);
                LocalDateTime by = Parser.parseDateTime(byStr);
                Task task = new Deadline(description, by, false);
                tasks.add(task);
                printTaskAdded(task.toString(), tasks.size());
            } else if (taskType.equalsIgnoreCase("event")) {
                String description = parsedData.get(1);
                String byStr = parsedData.get(2);
                String toStr = parsedData.get(3);
                LocalDateTime by = Parser.parseDateTime(byStr);
                LocalDateTime to = Parser.parseDateTime(toStr);
                Task task = new Event(description, by, to, false);
                tasks.add(task);
                printTaskAdded(task.toString(), tasks.size());
            } else {
                throw new IllegalArgumentException("Unknown task type.");
            }
            storage.save(tasks);
        } catch (Exception e) {
            System.out.println("  OOPS!!! " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private static void printTaskAdded(String msg, int taskCount){
        System.out.println("  Got it. I've added this task:");
        System.out.println("    " + msg);
        System.out.println("  Now you have " + taskCount + " tasks in the list.");
    }
}
