package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * Inherits from abstract Command class.
 * Handles execution of commands in this format:
 * <code>bye</code>
 */
public class ExitCommand extends Command {

    public ExitCommand(String taskInformation) {
        super(taskInformation);
    }


    /**
     * Executes commands in this format:
     * <code>bye</code> and prints goodbye message
     *
     * @param tasks   <code>TaskList</code> object which holds the taskList
     *                and various methods to operate on the taskList
     * @param ui      <code>UI</code> object which handles console output
     * @param storage <code>Storage</code> object which allows for reading
     *                result of executed command into preset task.txt file
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
