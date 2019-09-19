package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Encapsulates a command that adds a task into the tasks list of Duke.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task t) {
        task = t;
    }

    /**
     * Executes the adding command accordingly.
     *
     * @param database the database of Duke.
     * @param tasksList the tasks list of Duke.
     * @throws DukeException if the user's input is incorrect.
     */
    public String execute(DukeDatabase database, TaskList tasksList) throws DukeException {
        tasksList.addTask(task);
        return String.format("Got it I've added this task:\n%s\nNow you have %s in the list.\n",
                task.toString(), getTaskPhrase(tasksList.size()));
    }
}
