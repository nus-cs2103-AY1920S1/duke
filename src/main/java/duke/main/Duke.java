package duke.main;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents core of the Duke program.
 * Acts as a planner for the Duke user by allowing for CRUD functionality on Tasks specified by user.
 */
public class Duke {

    private static String LIST_PATH = "C:/Users/Yu Han Jeong/Desktop/CS2103T/duke/src/data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        storage = new Storage(LIST_PATH);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        }
        catch (DukeException ex) {
            tasks = new TaskList();
            ui.showError(ex.getMessage());
        }
    }

    /**
     * Main driver of Duke program. Uses list of tasks to store user specified activities.
     * Able to perform CRUD functionality when queried by user.
     *
     */
    public void run() {
        ui.greetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            }
            catch (DukeException ex) {
                ui.showError(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
    
}