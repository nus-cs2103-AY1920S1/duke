package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.main.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents the 'delete' command.
 */
public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the indexed task and saves this new task list in the hard disk
     *
     * @param storage the Storage object to update the tasks in the file
     * @param ui the Ui object dealing with interactions with the user
     * @param tasks the TaskList object containing the existing list of tasks
     */
    public String execute(Storage storage, Ui ui, TaskList tasks) throws DukeException {
        try {
            Task deleted = tasks.removeTask(index);
            storage.writeToFile(tasks);
            return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list",
                    deleted.toString(), tasks.getTasksSize());

        }
        catch (Exception e) {
            throw new DukeException(String.format("OOPS!!! Please input a number between 1 and %d after 'delete'",
                    tasks.getTasksSize()));
        }
    }
}
