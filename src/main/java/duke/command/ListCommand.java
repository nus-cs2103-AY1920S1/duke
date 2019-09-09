package duke.command;

import duke.UndoableStub;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;
import duke.module.UndoStack;

/**
 * Represents the "list" command supported by Duke.
 */
public class ListCommand extends Command {

    /**
     * Lists all {@code Task}s in the {@code TaskList} as {@code String}s.
     *
     * @param taskList List of tasks to manage.
     * @param undoStack Stack of {@code Undoable} commands.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes if necessary.
     */
    @Override
    public void execute(TaskList taskList, UndoStack undoStack, Ui ui, Storage storage) {
        // Add UndoableStub to the undoStack
        undoStack.addUndoable(new UndoableStub("list"));
        // Display the result to the user
        ui.printToUser(taskList.listAll());
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
