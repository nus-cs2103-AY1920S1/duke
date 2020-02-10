package czkay.duke.model.task;

import czkay.duke.exception.DukeException;
import czkay.duke.exception.InvalidTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the timed task given by the user.
 */
public abstract class TimedTask extends Task {
    LocalDateTime timestamp;
    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    TimedTask(String taskDescription, String rawTimestamp) throws DukeException {
        super(taskDescription);
        try {
            timestamp = LocalDateTime.parse(rawTimestamp, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException("You formatted your time incorrectly! "
                + "Please format it this way: dd/mm/yyyy hh:mm");
        }
    }

    @Override
    public boolean isTimed() {
        return true;
    }

    /**
     * Gets the timestamp of the timed task.
     * @return The timestamp of the timed task.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Formats the timed task such that it can be outputted in a readable form for the user.
     *
     * @return The description of the timed task.
     */
    @Override
    public abstract String toString();

}
