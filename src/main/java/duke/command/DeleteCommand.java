package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Encapsulates a command that deletes a task from the task list of duke.Duke.
 */
public class DeleteCommand extends Command {
    private DeleteType deleteType;

    /**
     * The type of delete command.
     */
    public enum DeleteType {
        INDEX;
    }

    /**
     * Constructs a DeleteCommand object.
     *
     * @param type type of delete command.
     * @param input user's input.
     */
    public DeleteCommand(DeleteType type, String input) {
        super(input);
        deleteType = type;
    }

    /**
     * Executes the delete command accordingly.
     *
     * @param tasksList the tasks list of duke.Duke.
     * @param database the database of duke.Duke.
     * @throws DukeException if the user's input is incorrect.
     */
    public String execute(DukeDatabase database, TaskList tasksList) throws DukeException {
        initialise(database, tasksList);

        Task task = null;
        if (DeleteType.INDEX.equals(deleteType)) {
            task = deleteTask();
        }

        String response = "";
        if (task != null) {
            response = String.format("Noted. I've removed this task:\n%s\nNow you have %s in the list.\n",
                    task.toString(), getTaskPhrase(taskList.size()));
        }

        return response;
    }

    /**
     * Deletes a task from the taskList.
     */
    private Task deleteTask() throws DukeException {
        try {
            int index = Integer.parseInt(input.substring(6).trim());
            Task task = taskList.removeTask(index - 1);

            return task;
        } catch (NumberFormatException e) {
            throw new DukeException("There can only be an integer after the word \"delete\"!");
        }
    }
}
