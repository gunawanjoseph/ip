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

/**
 * Represents a command that adds a task to the task list.
 * This command parses the data and creates the corresponding task type and adds it to the task list.
 */
public class AddCommand implements Command {
    private final ArrayList<String> parsedData;

    /**
     * Initializes an AddCommand with the parsedData
     * @param parsedData The data parsed from user input.
     */
    public AddCommand(ArrayList<String> parsedData) {
        this.parsedData = parsedData;
    }

    /**
     * Executes the command to add a task to the task list based on the parsed data.
     * @param tasks The list of tasks where the new task will be added
     * @param ui The user interface for showing messages
     * @param storage The storage object used to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String taskType = parsedData.get(0);
            Task task;
            if (taskType.equalsIgnoreCase("todo")) {
                String description = parsedData.get(1);
                task = new ToDo(description, false);
            }else if (taskType.equalsIgnoreCase("deadline")) {
                String description = parsedData.get(1);
                String byStr = parsedData.get(2);
                LocalDateTime by = Parser.parseDateTime(byStr);
                task = new Deadline(description, by, false);
            } else if (taskType.equalsIgnoreCase("event")) {
                String description = parsedData.get(1);
                String byStr = parsedData.get(2);
                String toStr = parsedData.get(3);
                LocalDateTime by = Parser.parseDateTime(byStr);
                LocalDateTime to = Parser.parseDateTime(toStr);
                task = new Event(description, by, to, false);
            } else {
                throw new IllegalArgumentException("Unknown task type.");
            }
            tasks.add(task);
            storage.save(tasks);
            return formatTaskAdded(task.toString(), tasks.size());
        } catch (Exception e) {
            return "  OOPS!!! " + e.getMessage();
        }
    }

    /**
     * Checks if this command is an exit command.
     * @return false, since this command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints a message confirming that a task has been added to the list.
     * @param msg The message to be displayed, including the task details.
     * @param taskCount The number of tasks currently int the task list.
     */
    private static String formatTaskAdded(String msg, int taskCount) {
        return "Got it. I've added this task:\n"
                + "  " + msg + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
    }
}
