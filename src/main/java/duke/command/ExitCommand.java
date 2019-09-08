package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Prints out the response when user exits the application.
     *
     * @return String representing the response.
     */
    public String execute(Storage storage, TaskList tasklist) {
        return "Bye. Hope to see you again soon!";
    }

}
