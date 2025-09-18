package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

/**
 * Represents the "List" command that displays all the tasks in the task list.
 */
public class ListCommand implements Command {
    /**
     * Executes the "List" command, which displays all tasks in the task list.
     * @param tasks The task list where tasks are stored
     * @param ui The user interface where messages are shown to the user.
     * @param storage The storage object where tasks are saved and laoded.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String str = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); ++i) {
            str = str + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return str;
    }

    /**
     * Checks if the command is an exit command.
     * @return false, since it is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
