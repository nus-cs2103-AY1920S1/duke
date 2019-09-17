package duke;

import duke.lib.datahandling.TaskList;
import duke.lib.common.DukeException;
import duke.lib.datahandling.DataStorage;
import duke.lib.datahandling.Parser;

/**
 * The main class of duke, which manages the running of the program.
 */
public class Duke {
    private Parser parser;

    /**
     * Handles the response from the input of the user.
     *
     * @param input the input of the user.
     * @return the response.
     */
    public String getResponse(String input) {
        try {
            return parser.parse(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Constructor which instantiates a ui, data storage, and attempts loading save dat from the filepath given.
     */
    public Duke( ) throws DukeException {
        DataStorage storage = new DataStorage();
        TaskList tasks;
        try {
            tasks = new TaskList();
            tasks.store(storage.load());
            parser = new Parser(tasks, storage);
        } catch (DukeException e) {
            tasks = new TaskList();
            parser = new Parser(tasks, storage);
            throw e;
        }

    }

    /**
     * Checks if the application has been flagged to exit.
     *
     * @return the exit flag.
     */
    public boolean isExit() {
        return parser.isExit();
    }
}
