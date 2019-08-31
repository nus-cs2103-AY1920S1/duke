package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Class representing a command to exit Duke.
 */
public class ExitCommand extends Command {
    /**
     * Creates a new ExitCommand. In particular, sets the exit variable of its superclass
     * to true so the application can exit.
     */
    public ExitCommand() {
        exit = true;
    }

    /**
     * Executes this command on the given task list.
     *
     * @param tl The task list.
     * @param storage The place where tasks will be stored.
     */
    public String execute(TaskList tl, Storage storage) {
        return "Bye!";
    }
}
