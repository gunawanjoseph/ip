package HawkerUncle;

import HawkerUncle.command.Command;
import HawkerUncle.parser.Parser;
import HawkerUncle.storage.Storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;

import java.io.IOException;

/**
 * Represents the main entry point for HawkerUncle application.
 * Manages the user interface, task list, and storage operations.
 */

public class HawkerUncle {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "./data/HawkerUncle.txt";

    /**
     * Constructor to initialize the HawkerUncle application.
     * Initializes the user interface, storage, and task list.
     * @param filePath The file path to lad the task data from.
     */
    public HawkerUncle(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e){
            tasks = new TaskList();
        }
    }

    /**
     * Starts and runs the application.
     * Displays the welcome message, reads commands from the user, parses the commands,
     * executes the corresponding command, and continues until an exit command "bye" is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Starts the HawkerUncle application.
     * @param args
     */
    public static void main(String[] args){
        new HawkerUncle(FILE_PATH).run();
    }

}