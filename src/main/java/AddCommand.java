package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represent a command to add a task to the list.
 */
public class AddCommand implements Command {
    private Task task;

    /**
     * Creates a command to add the task specified in the parameter to the list.
     * 
     * @param task the new task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the command and add the task. Takes in a tasklist to which the task
     * will be added, a ui which will output the message and a storage which writes
     * the task to a file
     * 
     * @param tasks   a tasklist containing all the existing tasks
     * @param ui      a ui which will output the message
     * @param storage a storage object which will write to the file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.write(tasks);
        return ui.addMessage(task, tasks);
    }
}