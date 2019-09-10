package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;

public class ExitCommand extends Command {

    /**
     * Initialises an ExitCommand.
     */
    public ExitCommand() {

    }

    /**
     * Executes the Exit Command and kills the duke session.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param storage The Storage for saving tasks to file.
     * @return The response string.
     */
    public String execute(TaskList tasks, Storage storage) {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Checks if it is a bye command.
     *
     * @return A boolean: true if it is a bye command.
     */
    public boolean isExit() {
        return true;
    }
}
