package duke.command;

import duke.exception.DukeException;
import duke.module.UndoStack;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

public class UndoCommand extends Command {

    @Override
    public void execute(TaskList taskList, UndoStack commandStack, Ui ui, Storage storage)
            throws DukeException {
        // TODO : javadocs
        commandStack.popLatest().undo(taskList, ui, storage);
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
