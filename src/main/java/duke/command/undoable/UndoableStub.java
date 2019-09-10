package duke.command.undoable;

import duke.module.AutoResponse;
import duke.module.Storage;
import duke.module.TaskList;

/**
 * Instance of this object is to be added by {@code Command}s that are not {@link Undoable};
 */
public enum UndoableStub implements Undoable {

    LIST("list"),
    FIND("find");


    private String commandName;
    UndoableStub(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Returns a message stating that {@code commandName} cannot be undone.
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes.
     */
    @Override
    public String[] undo(TaskList taskList, Storage storage) {
        return new String[] { String.format(AutoResponse.DUKE_UNDO_STUB, this.commandName) };
    }

}
