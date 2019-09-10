package duke.command.undoable;

import duke.exception.DukeException;

import duke.module.Storage;
import duke.module.TaskList;

public interface Undoable {

    /**
     * Undoes any command that implements this interface.
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes if necessary.
     * @return The result of undoing a command.
     * @throws DukeException If an exception occurs while undoing a command.
     */
    String[] undo(TaskList taskList, Storage storage) throws DukeException;

}
