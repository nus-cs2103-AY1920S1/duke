package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The command used to mark a task in the Duke Program as done.
 */
public class DoneCommand extends BasicCommand {
    public DoneCommand(String line) throws DukeException {
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

        // Get the index of the task in the list of tasks and retrieve the task
        // Throw a DukeException if the argument is not a number or if there is no task at given index.
        int index = Integer.parseInt(inputs[1]) - 1;
        tasks.markAsDone(index);

        storage.store(tasks);

        // Return the messages confirming the completion of the task
        return new String[] {
            "Nice! I've marked this task as done: ",
            tasks.get(index).toString()
        };
    }
}
