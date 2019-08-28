package duke.command;

/**
 * Data structure to wrap the command to list out the tasks in the task list
 */
public class ShowListCommand extends Command {

    /**
     * Constructs the command which displays the list of tasks in the task list
     */
    public ShowListCommand() {
        super(Type.LIST);
    }
}