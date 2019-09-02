package duke.command;

/**
 * A Command which is used to tell Duke to add a Task to the TaskList.
 */
public class AddTaskCommand extends Command {

    /**
     * Constructor to create a Command for to tell Duke to add a Task to the TaskList
     *
     * @param type       Type for adding a Deadline, Event, or Todo task
     * @param parameters Description of the task, and Time of the task (if applicable)
     */
    AddTaskCommand(Type type, String... parameters) {
        super(type, parameters);
    }
}