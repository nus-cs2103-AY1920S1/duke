package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * duke.Main class for the Duke application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    /**
     * Provides a constructor with zero parameters for JavaFX Launcher
     * to use.
     */
    public Duke() {
        // hardcoded storage file path
        storage = new HardDiskStorage("/data/duke.txt");
        tasks = new TaskList();
        ui = new TextUi(); // temporary
    }

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
     * Initialises and runs the Duke application.
     *
     * @param args  Standard arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Executes the user input and returns Duke's response.
     *
     * @param input     String of user-given input
     * @return          String of Duke's response to given input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String dukeResponse = c.execute(tasks, ui, storage);
            return dukeResponse;
        } catch (DukeException e) {
            return "Sorry, " + e.getMessage();
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

}
