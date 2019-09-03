package core;

import command.Command;
import exception.DukeException;
import task.TaskList;
import ui.Cli;
import ui.Ui;

/**
 * Duke Chat Class.
 *
 * <p>A Personal Assistant Chatbot that helps to keep track of various things.
 *
 * @author Marcus Ong
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke Constructor, defaults to CLI mode.
     *
     * @param filePath String containing path of data file for Duke.
     */
    public Duke(String filePath) {
        ui = new Cli();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Duke Constructor.
     *
     * @param filePath String containing path of data file for Duke.
     */
    public Duke(String filePath, Ui ui) {
        this.ui = ui;
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data/duke.txt", new Cli()).run();
    }

    /**
     * Run chat bot interaction.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ((Cli) ui).readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Consume user input from GUI.
     *
     * @param input String containing user input.
     * @return True if should exit program.
     */
    public boolean consumeUserInput(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }
}
