package duke.command;

import duke.util.DateTime;
import duke.task.Deadline;

/**
 * Class representing a command issued by the user to create a
 * Deadline object. Inherits from the NewTaskCommand abstract class.
 * @see NewTaskCommand
 * @see Deadline
 */
public class DeadlineCommand extends NewTaskCommand {

    public static final String KEYWORD = "deadline";

    /**
     * Returns a DeadlineCommand object that can be executed to
     * create a Deadline object and add it to the current TaskList.
     *
     * @param description description of Deadline object to be created
     * @param by DateTime object representing date and time to be associated with the Deadline object
     */
    public DeadlineCommand(String description, DateTime by) {
        super(new Deadline(description, by));
    }
}
