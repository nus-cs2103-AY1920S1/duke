package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.parser.Parser;
import duke.exception.DukeException;

/**
 * Represents a bot which tracks user's tasks.
 */
public class Duke {
    /** Storage to load, save and store user data. */
    private Storage storage = new Storage("data.txt");

    /** TaskList to store all the tasks. */
    private TaskList tasks;

    /** Boolean to check whether user is exiting Duke. */
    private boolean isExit = false;

    /**
     * Previously saved data will be loaded and converted into a list of tasks.
     * Otherwise, an empty list will be initialized.
     * @return notice notifying the user of whether data is loaded.
     */
    public String loadStorage() {
        String s = "Hello i'm Duke! What can i do for you?";

        try {
            tasks = new TaskList(storage.load());
            return "Successfully loaded previous data. \n" + s;
        } catch (DukeException e) {
            tasks = new TaskList();
            return e.getMessage() + "\n" + s;
        }
    }

    /**
     * Checks whether user is exiting Duke.
     * @return boolean signifying whether user is exiting Duke.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Gets response to the user input.
     * @param input commands users entered to be executed.
     * @return response to the user to be printed out.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            CommandResult result = c.execute(tasks, storage);
            isExit = result.isExit();
            return result.getFeedBack();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
