package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

/**
 * Abstract commmand class
 */
public abstract class Command {

    /**
     * Executes command
     * @param toDoList
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList toDoList, UI ui, Storage storage);

    /**
     * Determines if command exits
     * @return boolean
     */
    public abstract boolean isExit();
}