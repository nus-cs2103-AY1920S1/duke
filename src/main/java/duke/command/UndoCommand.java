package duke.command;

import duke.exception.DukeException;

import duke.module.CommandStack;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

public class UndoCommand extends Command {

    /**
     * Undoes the most recent action.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes if necessary.
     * @throws DukeException If an error occurs while undoing a command.
     */
    @Override
    public void execute(TaskList taskList, CommandStack commandStack, Ui ui, Storage storage)
            throws DukeException {
        ui.printToUser(commandStack.popUndo().undo(taskList, commandStack, storage));
    }

    /**
     * Returns the result of undoing the most recent action.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes.
     * @return The result of undoing the most recent action.
     * @throws DukeException If an error occurs while undoing a command.
     */
    @Override
    public String getResponse(TaskList taskList, CommandStack commandStack, Storage storage)
            throws DukeException {
        return String.join("\n", commandStack.popUndo().undo(taskList, commandStack, storage));
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
