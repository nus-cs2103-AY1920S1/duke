package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Main class for the Duke application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    /**
     * Sets up Duke's user interface, storage, and task list.
     *
     * @param filePath  Path to data file
     */
    public Duke(String filePath) {
        ui = new TextUi();
        storage = new HardDiskStorage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main application by interacting with user input.
     *
     * <p>Duke begins by printing a welcome message. Subsequently, it scans
     * for user input, then validates and processes it accordingly. This is
     * repeated until the command to exit ("bye") is received.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
                // TODO: Add "help" feature: list all supported commands
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Initialises and runs the Duke application.
     *
     * @param args  Standard arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}