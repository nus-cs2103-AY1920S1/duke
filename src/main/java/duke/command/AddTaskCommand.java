package duke.command;

/**
 * Data structure to wrap the command to add a Deadline, Event, or ToDo task to the task list
 */
public class AddTaskCommand extends Command {

    /**
     * Constructor to create a command for adding a task
     * @param type Type for adding a Deadline, Event, or Todo task
     * @param parameters Description of the task, and Time of the task (if applicable)
     *
     */
    public AddTaskCommand(Type type, String... parameters) {
        super(type, parameters);
    }
}