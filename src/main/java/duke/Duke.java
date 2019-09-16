package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeShutDownException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * GUI Chat assistant that keep tracks of tasks.
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
     * Executes user command and retrieves response, if applicable.
     *
     * @param input user command to be executed
     * @return response to be printed to user
     */
    public String getResponse(String input) throws DukeShutDownException {
        String response;

        try {
            Command c = Parser.parseForCommands(input); // send it off to be parsed
            c.initialize(this.storage, this.taskList, this.ui);
            response = c.execute();
            assert (response != null);
        } catch (DukeShutDownException shutDownSignal) {
            throw shutDownSignal; // throws signal to GUI to handle
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}