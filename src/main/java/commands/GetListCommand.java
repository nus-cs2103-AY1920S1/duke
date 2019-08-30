package commands;

import logic.*;

/**
 * Encapsulates command to get and print an ordered list of tasks
 */
public class GetListCommand extends Command {

    /**
     * Overridden Method to execute the GetList command
     *
     * @param tasks   list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getTaskList());
    }
}
