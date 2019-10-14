package duke.command;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.task.TaskList;
import duke.storage.Storage;

/**
 * Represent a command to find a task based on it's description.
 */
public class FindCommand implements Command {
    private String search;

    /**
     * Creates a command that find for a search string within the description.
     * 
     * @param search a string of the task to be found
     */
    public FindCommand(String search) {
        this.search = search;
    }

    /**
     * Execute the command and find the tasks matching the string. Takes in a
     * tasklist to which will be search, a ui which will output the message and a
     * storage which updates the task in the file
     * 
     * @param tasks   a tasklist containing all the existing tasks
     * @param ui      a ui which will output the message
     * @param storage a storage object which will write to the file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.searchMessage(tasks.find(search));
    }
}