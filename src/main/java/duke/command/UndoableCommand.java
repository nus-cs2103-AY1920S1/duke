package duke.command;

import duke.exception.DukeException;
import duke.tasklist.Tasklist;

/**
 * Represents a command that can be undone. It extends a normal command.
 *
 * <p> Only commands that change the task list state are undoable.
 *  GUI outputs do not need to be undone.
 *  Undoable commands are: Add, delete, done.</p>
 */
public abstract class UndoableCommand extends Command {

    protected abstract void undo(Tasklist tasklist);

    /**
     * Tells user that the command was undone.
     * @return a string message.
     */
    protected String undoneMessage() {
        return "This command was undone: ";
    }
}
