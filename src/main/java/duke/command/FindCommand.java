package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Formatter;


/**
 * The command used to find tasks with a keyword.
 */
public class FindCommand extends BasicCommand {
    public FindCommand(String line) throws DukeException {
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null : "tasks cannot be null";
        assert storage != null : "storage cannot be null";

        // Retrieve the keyword
        String keyword = getInputs()[1];

        // Filter the tasks that contain the keyword
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                filteredTasks.add(task);
            }
        }

        // Return the formatted list of tasks
        return Formatter.formatTasks(filteredTasks);
    }
}
