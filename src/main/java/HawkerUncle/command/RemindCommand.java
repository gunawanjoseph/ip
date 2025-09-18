package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.task.Deadline;
import HawkerUncle.task.Event;
import HawkerUncle.task.Task;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

import java.time.LocalDateTime;

public class RemindCommand implements Command{


    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList reminders = new TaskList();

        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            if (!task.getDone()) {
                if (task instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    if (d.getBy().isAfter(now)) {
                        reminders.add(d);
                    }
                } else if (task instanceof Event) {
                    Event e = (Event) task;
                    if (e.getFrom().isAfter(now) || e.getTo().isAfter(now)) {
                        reminders.add(e);
                    }
                }
            }
        }
        return Ui.showReminders(reminders);
    }
}
