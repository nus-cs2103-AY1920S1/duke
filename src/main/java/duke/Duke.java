package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;

/**
 * A task management application.
 */
public class Duke {
    private static final String STORAGE_PATHNAME = "data/tasks.txt";
    private boolean isActive;
    private Storage storage;
    private TaskList tasks;

    /**
     * Returns a {@link Duke}.
     */
    public Duke() throws DukeException, IOException {
        isActive = true;
        storage = new Storage(STORAGE_PATHNAME);
        tasks = new TaskList(storage.load());
    }

    /**
     * Parses and executes the input given.
     *
     * @param input The input to parse and execute
     * @return The response from Duke.
     */
    public String getResponse(String input) throws IOException {
        try {
            Command c = Parser.parse(input);
            c.setData(tasks, storage);
            CommandResult result = c.execute();
            isActive = !c.isExit();
            return result.feedback;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public boolean isActive() {
        return isActive;
    }
}

