public class ByeCommand implements Command{
    @Override
    public void execute(TaskList task, Ui ui, Storage storage){
        System.out.println("Bye. Hope to see you again soon!");
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
