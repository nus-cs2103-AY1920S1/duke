package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Abstract commmand class.
 */
public abstract class Command {

    /**
     * Executes command.
     *
     * @param toDoList to do list
     * @param ui       ui
     * @param storage  storage
     */
    public abstract void execute(TaskList toDoList, UI ui, Storage storage);

    /**
     * Determines if command exits.
     *
     * @return boolean
     */
    public abstract boolean isExit();
}