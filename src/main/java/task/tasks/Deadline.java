package task.tasks;

import error.UnknownCommandException;
import error.task.InvalidArgumentsException;
import error.task.UnknownDateTimeException;
import util.DukeDateTime;

import java.time.LocalDateTime;
import java.util.Optional;

/***
 * <p>
 * Task that needs to be completed by a specified date.
 * </p>
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /***
     * <p>
     * Deadline constructor.
     * </p>
     * @param arguments description and time separated by a "/by" separator.
     * @throws UnknownDateTimeException if date and time is given in the incorrect format.
     * @throws InvalidArgumentsException if arguments do not match required format.
     */
    public Deadline(String arguments) throws UnknownDateTimeException, InvalidArgumentsException {
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

    private LocalDateTime getTiming(String arguments) throws UnknownDateTimeException, InvalidArgumentsException {
        try {
            String dateTime = arguments.split(" /by ")[1];
            return DukeDateTime.parseDateTime(dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentsException("\"/by\"");
        }
    }
}
