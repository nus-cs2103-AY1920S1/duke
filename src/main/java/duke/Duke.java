package duke;

import duke.command.Command;
import duke.command.CommandResponse;
import duke.exception.DukeIoException;
import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Main Driver class housing the infinite loop.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Loads duke with task data.
     *
     * @param filePath the file path of the data file from project root.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = storage.readFromDisk(); // leave index 0 empty for clarity
        } catch (DukeIoException e) {
            System.err.println(ui.showError(e));
            this.taskList = new TaskList(); // only load the taskList if no error
        }
    }

    /**
     * Default constructor. JavaFX GUI can't run without declaring this.
     */
    public Duke() {
        this("data/duke.txt");
    }

    /**
     * The main loop for Duke.
     */
    public CommandResponse getResponse(String fullCommand) {
        Command c = Parser.parse(fullCommand);
        return c.execute(taskList, ui, storage);
    }
}
