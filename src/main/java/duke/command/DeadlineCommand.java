package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.DeadlineTask;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The command used to create a deadline task in the Duke Program.
 */
public class DeadlineCommand extends BasicCommand {
    public DeadlineCommand(String line) throws DukeException {
        super(line);
        validate();
    }

    /**
     * Gets the details of the command as an array of strings.
     *
     * @return an array of strings, the first element being the description and the second element being the deadline.
     */
    public String[] getDetails() {
        return super.line.split("deadline\\s*", 2)[1].split("\\s+/by\\s+", 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate() throws DukeException {
        // Throw a DukeException if there is no input
        if (line.equals("deadline")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        // Split the line by a regex and store the information in the details
        String[] details = getDetails();

        // Throw a DukeException if input is invalid
        if (details.length < 2 || details[0].isEmpty() || details[1].isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description or the deadline for this task is empty.");
        }

        Task task = new DeadlineTask(details[0], details[1]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null : "tasks cannot be null";
        assert storage != null : "storage cannot be null";

        String[] details = getDetails();

        // Create a new task using the information stored in details
        Task task = new DeadlineTask(details[0], details[1]);

        // Add the new task to the list of tasks
        tasks.add(task);
        storage.store(tasks);

        // Return the messages confirming the addition of the task
        return new String[] {
            "Got it. I've added this task:",
            task.toString(),
            "Now you have " + tasks.size() + " tasks in the list."
        };
    }
}
