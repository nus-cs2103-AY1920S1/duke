package duke;

import duke.command.Undoable;

import duke.exception.DukeException;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

/**
 * Instance of this object is to be added by {@code Command}s that are not {@link Undoable};
 */
public class UndoableStub implements Undoable {

    private String commandName;

    public UndoableStub(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Prints a message stating that {@code commandName} cannot be undone.
     *
     * @param taskList List of tasks to manage.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes.
     * @throws DukeException This method will never throw DukeException.
     */
    @Override
    public void undo(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printToUser(String.format(AutoResponse.DUKE_UNDO_STUB, this.commandName));
    }
}
