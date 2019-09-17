package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Abstract commmand class.
 */
public abstract class Command {

    /**
     * Executes and returns command.
     *
     * @param toDoList to do list
     * @param ui       ui
     * @param storage  storage
     * @return Command string
     */
    public abstract String execute(TaskList toDoList, UI ui, Storage storage);

    /**
     * Determines if command exits.
     *
     * @return boolean
     */
    public abstract boolean isExit();
}