package command;

import task.TaskList;
import ui.UserInterface;
import duke.Storage;

/**
 * Specifies the 'list' action to list out all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs the List Command object.
     */
    public ListCommand() {}

    /**
     * Executes the command and runs the related logic flow.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Tasks storage: load & save.
     */
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        super.message = "Here are the tasks in your list:\n";
        super.message += tasks.list();
    }
}