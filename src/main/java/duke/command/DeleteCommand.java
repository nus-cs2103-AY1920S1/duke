package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The command used to delete tasks from the Duke Program.
 */
public class DeleteCommand extends BasicCommand {
    public DeleteCommand(String line) throws DukeException {
        super(line);
        validate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate() throws DukeException {
        String[] inputs = getInputs();

        // Throw a DukeException if there are wrong number of arguments for the command
        checkIfCorrectNumberOfArguments(inputs, 1);

        try {
            Integer.parseInt(inputs[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The argument should be a number.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null : "tasks cannot be null";
        assert storage != null : "storage cannot be null";

        String[] inputs = getInputs();

        // Get the index of the task in the list of tasks and remove the task
        int index = Integer.parseInt(inputs[1]) - 1;
        Task task = tasks.remove(index);

        storage.store(tasks);

        // Return the messages confirming the deletion of the task
        return new String[]{
            "Noted. I've removed this task: ",
            task.toString(),
            "Now you have " + tasks.size() + " tasks in the list."
        };
    }
}
