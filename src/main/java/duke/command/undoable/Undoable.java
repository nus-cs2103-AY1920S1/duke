package duke.command.undoable;

import duke.exception.DukeException;

import duke.module.CommandStack;
import duke.module.Storage;
import duke.module.TaskList;

public interface Undoable {

    /**
     * Undoes any command that implements this interface.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes if necessary.
     * @return The result of undoing a command.
     * @throws DukeException If an exception occurs while undoing a command.
     */
    String[] undo(TaskList taskList, CommandStack commandStack, Storage storage) throws DukeException;

    /**
     * Redoes any command that implements this interface.
     *
     * @param taskList List of tasks to manage.
     * @param commandStack Stack of {@code Undoable} commands.
     * @param storage Storage to save any changes if necessary.
     * @return The result of redoing a command.
     * @throws DukeException If an exception occurs while redoing a command.
     */
    String[] redo(TaskList taskList, CommandStack commandStack, Storage storage) throws DukeException;

}
