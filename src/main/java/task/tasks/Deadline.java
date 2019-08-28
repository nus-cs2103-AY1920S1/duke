package task.tasks;

import error.task.UnknownDateTimeException;
import util.DukeDateTime;

import java.time.LocalDateTime;
import java.util.Optional;

/***
 * Task that needs to be completed by a specified date.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /***
     * Deadline constructor
     * @param arguments description and time separated by a "/by" separator.
     * @throws UnknownDateTimeException if date and time is given in the incorrect format.
     */
    Deadline(String arguments) throws UnknownDateTimeException {
        super(getDescription(arguments), TaskKeyword.DEADLINE);
        this.by = getTiming(arguments);
    }

    @Override
    protected String getTaskStringCode() {
        return "D";
    }

    @Override
    protected Optional<String> getTaskExtraDetails() {
        String byString = DukeDateTime.getString(by);
        return Optional.of(String.format("by: %s", byString));
    }

    private static String getDescription(String arguments) {
        return arguments.split(" /by ")[0];
    }

    private LocalDateTime getTiming(String arguments) throws UnknownDateTimeException {
        String dateTime = arguments.split(" /by ")[1];
        return DukeDateTime.parseDateTime(dateTime);
    }
}
