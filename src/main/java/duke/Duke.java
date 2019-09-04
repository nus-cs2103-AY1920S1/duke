package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeShutDownException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * CLI Chat assistant that keep tracks of tasks.
 * Will be developed incrementally over the course
 * of CS2103.
 */
public class Duke {
    // object attributes
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Returns a Duke object, which can be used
     * to start the chat assistant driver loop.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            this.taskList = new TaskList(this.storage.loadFromDisk());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    /**
     * Returns a Duke object with a specified load file path.
     * Its internal task list will be loaded from the filepath
     * specified by the first command line argument.
     *
     * @param filePath path to saved file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.taskList = new TaskList(this.storage.loadFromDisk());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        String response;

        try {
            Command c = Parser.parseForCommands(input); // send it off to be parsed
            c.initialize(this.storage, this.taskList, this.ui);
            response = c.execute();
        } catch (DukeShutDownException e) {
            response = Ui.GOODBYE;
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}