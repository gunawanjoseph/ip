package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

import java.io.IOException;

public class UnmarkCommand implements Command {
    private final int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (idx < 0 || idx >= tasks.size()) throw new IndexOutOfBoundsException("Task number is invalid.");
        tasks.get(idx).setDone(false);

        try {
            storage.save(tasks);
        } catch(IOException e) {
            System.out.println("Task is not saved");
        }

        System.out.println("  OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(idx));
    }
    @Override public boolean isExit() {
        return false;
    }
}
