package duke.command;

import duke.task.Event;

import java.util.Date;

/**
 * Represents a {@link Command} to add an {@link Event}.
 */
public class EventCommand extends AddCommand {

    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a task with a date" + System.lineSeparator()
            + "Usage: " + COMMAND_WORD + " <description> /at <date>";

    public EventCommand(String description, Date date) {
        super(new Event(description, date));
    }
}
