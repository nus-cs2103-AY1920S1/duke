package logic;

import model.Tasklist;
import storage.Storage;
import ui.UI;

public interface Command {

    /**
     * Executes the Command
     *
     * @param tasks   the TaskList of Tasks
     * @param ui      The User Interface
     * @param storage Storage
     * @return a String that represents the response of the executed command
     */
    String execute(Tasklist tasks, UI ui, Storage storage);

    /**
     * Checks if the Command executes would result in an exit
     *
     * @return true if it is an exit command, false otherwise
     */
    boolean isExit();

}
