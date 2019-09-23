package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TodoTask;
import duke.task.TodoWithDateRangeTask;

/**
 * The command used to create a todo task in the Duke Program.
 */
public class TodoCommand extends BasicCommand {
    public TodoCommand(String line) throws DukeException {
        super(line);
        validate();
    }

    /**
     * Gets the details of the command as an array of strings.
     *
     * @return an array of strings, the first element being the description of the todo task.
     */
    public String[] getDetails() {
        return line.split("todo\\s*", 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate() throws DukeException {
        // Throw a DukeException if there are no arguments
        if (line.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null : "tasks cannot be null";
        assert storage != null : "storage cannot be null";

        // Split the line by a regex and store the information in the details
        String[] details = getDetails();

        String[] extraDetails = details[1].split("\\s+/between\\s+", 2);

        // Create a new task using the information stored in details
        Task task = null;
        if (extraDetails.length == 1) {
            task = new TodoTask(extraDetails[0]);
        } else {
            String[] dateRangeDetails =  extraDetails[1].split("\\s+/and\\s+");
            if (dateRangeDetails.length == 1) {
                throw new DukeException("Invalid format");
            } else {
                task = new TodoWithDateRangeTask(extraDetails[0], dateRangeDetails[0], dateRangeDetails[1]);
            }
        }


        // Add the new task to the list of tasks
        tasks.add(task);

        storage.store(tasks);

        // Return the messages confirming the addition of the task
        return new String[]{
            "Got it. I've added this task:",
            task.toString(),
            "Now you have " + tasks.size() + " tasks in the list."
        };
    }
}
