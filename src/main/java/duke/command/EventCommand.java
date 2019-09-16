package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.task.Event;

import java.time.LocalDateTime;

/**
 * A class representing add a event task command.
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

    /**
     * Returns a string of help information for the event command.
     * @return a string of help information for the event command.
     */
    public static String getCommandHelpInfo() {
        return "The format of event command is: event <taks description> /at <DD/MM/YYYY HH:MM>";
    }
}
