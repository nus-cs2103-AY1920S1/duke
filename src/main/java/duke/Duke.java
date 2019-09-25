package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.ui.SpeechMaker;
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
     * Sets up user interface, storage, and task list for the Duke application.
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
     * Executes the given user input and returns Snowball's response.
     *
     * @param input String of user-given input.
     * @return String of Snowball's response to given input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return SpeechMaker.getApologyMessage(e.getMessage());
        }
    }
}
