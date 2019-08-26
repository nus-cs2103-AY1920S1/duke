import java.time.LocalDateTime;

/**
 * A class representing a add deadline task command.
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";

    /**
     * Class constructor specifying the task description and deadline.
     * @param description the description of the task.
     * @param dateTime the deadline of the task.
     * @throws IllegalDescriptionException If the description is illegal.
     */
    public DeadlineCommand(String description, LocalDateTime dateTime)
            throws IllegalDescriptionException {
        super(new Deadline(description, dateTime));
    }
}
