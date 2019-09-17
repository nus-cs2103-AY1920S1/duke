package duke.command;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;
import duke.module.CommandStack;

/**
 * Represents the "list" command supported by Duke.
 */
public class ListCommand extends Command {

    /**
     * Lists all {@code Task}s in the {@code TaskList} as {@code String}s.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes if necessary.
     */
    @Override
    public void execute(TaskList taskList, CommandStack commandStack, Ui ui, Storage storage) {
        // Display the result to the user
        ui.printToUser(taskList.listAll());
    }

    /**
     * Returns the response to this ListCommand.
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes if necessary.
     */
    @Override
    public String getResponse(TaskList taskList, CommandStack commandStack, Storage storage) {
        return String.join("\n", taskList.listAll());
    }

    /**
     * Returns false.
     *
     * @return False.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }

}
