package command;

import exception.DukeUndoException;
import storage.Storage;
import task.TaskList;
import task.UndoStack;
import ui.Ui;

/**
 * Command to undo an user action.
 */
public class UndoCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeUndoException {
        tasks.undo(UndoStack.previousTaskList());
        return "Your last action has been undone!";
    }
}
