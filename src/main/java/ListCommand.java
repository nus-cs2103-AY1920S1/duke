package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represent a command to list all the tasks in the list.
 */
public class ListCommand implements Command {

    /**
     * Execute the command and list all the task. Takes in a tasklist to which the
     * task will be referenced, a ui which will output the message and a storage
     * which writes the task to a file
     * 
     * @param tasks   a tasklist containing all the existing tasks
     * @param ui      a ui which will output the message
     * @param storage a storage object which will write to the file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.printList(tasks);
    }
}