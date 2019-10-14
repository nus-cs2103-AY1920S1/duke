package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand implements Command {
    private int index;

    /**
     * Creates a command to delete the specified task from the list.
     * 
     * @param index integer value of the task index in the list
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the command and delete the task. Takes in a tasklist to which the
     * task will be deleted, a ui which will output the message and a storage which
     * remove the task from the file
     * 
     * @param tasks   a tasklist containing all the existing tasks
     * @param ui      a ui which will output the message
     * @param storage a storage object which will write to the file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.removeTask(index);
        storage.write(tasks);
        return ui.deleteMessage(t, tasks);
    }
}