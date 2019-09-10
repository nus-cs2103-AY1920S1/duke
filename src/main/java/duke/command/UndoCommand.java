package duke.command;

import duke.exception.DukeException;

import duke.module.UndoStack;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

public class UndoCommand extends Command {

    /**
     * Undoes the most recent action.
     *
     * @param taskList List of tasks to manage.
     * @param undoStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes if necessary.
     * @throws DukeException When something goes wrong when undoing a command.
     */
    @Override
    public void execute(TaskList taskList, UndoStack undoStack, Ui ui, Storage storage)
            throws DukeException {
        ui.printToUser(undoStack.popLatest().undo(taskList, storage));
    }

    /**
     * Returns the result of undoing the most recent action.
     *
     * @param taskList List of tasks to manage.
     * @param undoStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes.
     * @return The result of undoing the most recent action.
     * @throws DukeException When something goes wrong when undoing a command.
     */
    @Override
    public String getResponse(TaskList taskList, UndoStack undoStack, Storage storage)
            throws DukeException {
        return String.join("\n", undoStack.popLatest().undo(taskList, storage));
    }

    /**
     * Returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
