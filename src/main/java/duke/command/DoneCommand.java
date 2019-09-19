package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Encapsulates a command which marks a task in the tasks list of Duke as Done.
 */
public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int i) {
        index = i;
    }

    /**
     * Executes the update command accordingly.
     *
     * @param tasksList the tasks list of Duke.
     * @param database the database of Duke.
     * @throws DukeException if the user's input is incorrect.
     */
    public String execute(DukeDatabase database, TaskList tasksList) throws DukeException {
        Task task = tasksList.getTask(index);
        task.markAsDone();

        return String.format("Nice! I've marked this task as done:\n%s\n", task.toString());
    }
}
