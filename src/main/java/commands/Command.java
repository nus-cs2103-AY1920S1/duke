package commands;

import components.Storage;
import components.Ui;
import components.TaskList;

public interface Command {
    /**
     * @param ui       refers to the Ui instance. This object provides access to the Scanner instance.
     * @param storage  refers to the Storage instance. This object allows any commands that edit the Tasklist to save
     *                 changes onto file.
     * @param taskList refers to the TaskList instance. This object allows the list of items to change.
     */
    void execute(Ui ui, Storage storage, TaskList taskList);

    /**
     * All commands will return false with the exception of EndSessionCommand. Used to determine if Ui should remain
     * active.
     * @return
     */
    default boolean isExit() {
        return false;
    }
}
