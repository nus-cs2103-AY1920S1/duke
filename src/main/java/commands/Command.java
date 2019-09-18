package commands;

import components.Storage;
import components.TaskList;

/**
 * An interface for creating commands.
 */
public interface Command {
    /**
     * @param storage  refers to the Storage instance. This object allows any commands that edit the Tasklist to save
     *                 changes onto file.
     * @param taskList refers to the TaskList instance. This object allows the list of items to change.
     */
    String[] execute(Storage storage, TaskList taskList) throws DukeException;

    /**
     * All commands will return false with the exception of EndSessionCommand. Used to determine if Ui should remain
     * active.
     *
     * @return whether Duke should continue staying active or terminate.
     */
    default boolean isExit() {
        return false;
    }
}
