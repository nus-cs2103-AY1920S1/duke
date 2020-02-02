package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.task.Event;

import java.time.LocalDateTime;

/**
 * A class representing a event task command.
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";

    /**
     * Class constructor specifying the task description and time.
     * @param description the description of the task.
     * @param dateTime the time at which the event happens.
     * @throws IllegalDescriptionException If the description is illegal.
     */
    public EventCommand(String description, LocalDateTime dateTime)
            throws IllegalDescriptionException {
        super(new Event(description, dateTime));
    }
}
