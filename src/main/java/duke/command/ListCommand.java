package duke.command;

import duke.Duke;
/**
 * The class handles methods involving listing all the tasks.
 */

public class ListCommand extends Command {
    /**
     * Public constructor for ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the List command (i.e. lists all the tasks).
     * @return All the tasks in String format.
     */
    @Override
    public String executeCommand() {
        return Duke.taskList.view();
    }
}
