package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

import java.io.IOException;

public class MarkCommand implements Command {
    private final int idx;
    public MarkCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (idx < 0 || idx >= tasks.size()) throw new IndexOutOfBoundsException("Task number is invalid.");
        tasks.get(idx).setDone(true);

        try {
            storage.save(tasks);
        } catch(IOException e) {
            System.out.println("Task is not saved");
        }

        System.out.println("  Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(idx));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
