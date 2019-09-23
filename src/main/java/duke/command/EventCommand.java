package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.EventTask;
import duke.task.EventWithEndDateTask;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The command used to create an event task in the Duke Program.
 */
public class EventCommand extends BasicCommand {
    public EventCommand(String line) throws DukeException {
        super(line);
        validate();
    }

    /**
     * Gets the details of the command as an array of strings.
     *
     * @return an array of strings, the first element being the description and the second being the event dates.
     */
    public String[] getDetails() {
        return line.split("event\\s*", 2)[1].split("\\s+/at\\s+", 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate() throws DukeException {
        // Throw a DukeException if there is no input
        if (line.equals("event")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        // Split the line by a regex and store the information in the details
        String[] details = getDetails();

        // Throw a DukeException if input is invalid
        if (details.length < 2 || details[0].isEmpty() || details[1].isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description or the date & time for this task is empty.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null : "tasks cannot be null";
        assert storage != null : "storage cannot be null";

        String[] details = getDetails();

        String[] dateTimes = details[1].split("\\s+/to\\s+", 2);

        // Create a new task using the information stored in details
        Task task = dateTimes.length == 1
            ? new EventTask(details[0], dateTimes[0])
            : new EventWithEndDateTask(details[0], dateTimes[0], dateTimes[1]);

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
