package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println("  " + (i + 1) + "." + tasks.get(i).toString());
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
