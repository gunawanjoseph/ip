package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.task.Task;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

import java.util.ArrayList;

/**
 *  Represents the "Find" command to remove a task from the task list
 */
public class FindCommand implements Command {
    private final String word;

    /**
     * Initializes the FindCommand with a word to search for
     * @param word The word to search for in the task description
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Executes the 'Find' command by searching for tasks that contain the given keyword in their description
     * @param tasks The task list where tasks are stored
     * @param ui The user interface where messages are shown to the user.
     * @param storage The storage object where tasks are saved and loaded.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(word.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            System.out.println("  No task found");
        } else {
            System.out.println("  Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); ++i) {
                System.out.println("  " + (i + 1) + "." + matchingTasks.get(i).toString());
            }
        }
    }

    /**
     * Checks if the command is an exit command.
     * @return false, since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
