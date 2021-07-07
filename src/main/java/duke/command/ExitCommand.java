package duke.command;

import duke.exception.DukeIoException;

import duke.module.AutoResponse;
import duke.module.CommandStack;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

/**
 * Represents the "exit" command supported by Duke.
 */
public class ExitCommand extends Command {

    /**
     * Quits Duke.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes if necessary.
     * @throws DukeIoException If an error occurs while saving.
     */
    @Override
    public void execute(TaskList taskList, CommandStack commandStack, Ui ui, Storage storage)
            throws DukeIoException {
        // Save taskList before quitting
        storage.saveTasks(taskList);
        ui.bye();
    }

    /**
     * Returns the response to command "bye."
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes if necessary.
     */
    @Override
    public String getResponse(TaskList taskList, CommandStack commandStack, Storage storage)
            throws DukeIoException {
        // Save taskList before quitting
        storage.saveTasks(taskList);
        return AutoResponse.DUKE_BYE;
    }

    /**
     * Returns true.
     *
     * @return True.
     */
    @Override
    public boolean shouldExit() {
        return true;
    }

}
