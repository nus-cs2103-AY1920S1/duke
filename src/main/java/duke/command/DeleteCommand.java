package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Encapsulates a command that deletes a task from the tasks list of Duke.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int i) {
        index = i;
    }

    /**
     * Executes the delete command accordingly.
     *
     * @param tasksList the tasks list of duke.Duke.
     * @param database the database of duke.Duke.
     * @throws DukeException if the user's input is incorrect.
     */
    public String execute(DukeDatabase database, TaskList tasksList) throws DukeException {
        Task task = tasksList.removeTask(index - 1);

        return String.format("Noted. I've removed this task:\n%s\nNow you have %s in the list.\n",
                    task.toString(), getTaskPhrase(tasksList.size()));
    }
}
