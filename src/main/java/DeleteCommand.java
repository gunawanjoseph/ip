import java.io.IOException;

public class DeleteCommand implements Command{
    private final int idx;
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (idx < 0 || idx >= tasks.size()) throw new IndexOutOfBoundsException("Task number is invalid.");
        Task removed = tasks.remove(idx);

        try {
            storage.save(tasks);
        } catch(IOException e) {
            System.out.println("Task is not saved");
        }

        System.out.println("  Noted. I've removed this task:");
        System.out.println("    " + removed);
        System.out.println("  Now you have " + tasks.size() + " tasks in the list");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
