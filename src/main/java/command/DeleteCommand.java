package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

/**
 * A DeleteCommand object to deal with deleting a specific task from the task list.
 */
public class DeleteCommand extends Command {

    private int taskID;

    /**
     * Constructs a DeleteCommand object to deal with deleting tasks
     * @param taskID The task ID to be deleted.
     */
    public DeleteCommand(int taskID) {
        super();
        this.taskID = taskID;
    }

    /**
     * Executes the command to delete a task identified by its task ID.
     * @param tasks     The existing task list
     * @param ui        The Ui object which interacts with the current user
     * @param storage   The Storage object which reads and writes to a specified file
     * @throws DukeException If task ID is out of bounds
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskID > tasks.size()) {
            throw new DukeException("Invalid task ID. Please Enter a task ID between 1 and " + tasks.size());
        }
        Task removedTask = tasks.deleteTask(taskID);
        ui.dukeEcho("Noted. I've removed this task:", removedTask.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }

    @Override
    public String toString(){
        return "Delete " + this.taskID;
    }
}
