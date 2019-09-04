package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Encapsulates a command which updates a task in the tasks list of duke.Duke.
 */
public class UpdateCommand extends Command {
    private UpdateType updateType;

    /**
     * The type of update.
     */
    public enum UpdateType {
        DONE;
    }

    /**
     * Constructs an UpdateCommand object.
     * @param type the type of update.
     * @param input user's input.
     */
    public UpdateCommand(UpdateType type, String input) {
        super(input);
        updateType = type;
    }

    /**
     * Executes the update command accordingly.
     *
     * @param tasksList the tasks list of duke.Duke.
     * @param database the database of duke.Duke.
     * @throws DukeException if the user's input is incorrect.
     */
    public String execute(DukeDatabase database, TaskList tasksList) throws DukeException {
        initialise(database, tasksList);

        Task task = null;
        if (UpdateType.DONE.equals(updateType)) {
            task = markTaskAsDone();
        }

        String response = "";
        if (task != null) {
            response = String.format("Nice! I've marked this task as done:\n%s\n", task.toString());
        }

        return response;
    }

    /**
     * Mark a task in the task list as done.
     */
    private Task markTaskAsDone() throws DukeException {
        try {
            int index = Integer.parseInt(input.substring(4).trim());

            Task task = taskList.getTask(index - 1);
            task.markAsDone();

            return task;
        } catch (NumberFormatException e) {
            throw new DukeException("There can only be an integer after the word \"done\"!");
        }
    }
}
