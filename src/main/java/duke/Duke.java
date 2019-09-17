package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.HardDiskStorage;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TextUi;

/**
 * Main class for the Duke application.
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
        ui = new TextUi();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        assert tasks != null;
    }

    /**
     * Sets up Duke's user interface, storage, and task list.
     *
     * @param filePath Path to data file.
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
        assert tasks != null;
    }

    /**
     * Executes the given user input and returns Duke's response.
     *
     * @param input String of user-given input.
     * @return String of Duke's response to given input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return "Sorry, " + e.getMessage();
        }
    }
}
