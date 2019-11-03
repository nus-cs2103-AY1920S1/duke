package command;

import main.Archive;
import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

/**
 * A DoneCommand Object to deal with marking a task as complete.
 */
public class DoneCommand extends Command {

    private int taskID;

    /**
     * Creates a DoneCommand object to deal with marking a task as complete.
     * @param taskID The ID of the task to be marked as done.
     */
    public DoneCommand(int taskID) {
        super();
        this.taskID = taskID;
    }

    /**
     * Executes the command to mark a task as complete.
     *
     * @param tasks     The existing task list
     * @param ui        The Ui object which interacts with the current user
     * @param storage   The Storage object which reads and writes to a specified file
     * @param archive   The Archive object for archiving purposes
     * @return          The message to be displayed upon successful execution
     * @throws DukeException If task ID provided is out of bounds
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, Archive archive) throws DukeException {
        if (this.taskID > tasks.size() || this.taskID <= 0) {
            throw new DukeException("Invalid task ID. Please Enter a task ID between 1 and " + tasks.size());
        }
        assert this.taskID <= tasks.size() && this.taskID > 0 : "Task ID is invalid.";
        Task completedTask = tasks.finishTask(taskID);
        String res = ui.dukeEchoString("Nice! I've marked this task as done:", completedTask.toString());
        storage.save(tasks);
        return res;
    }
}
