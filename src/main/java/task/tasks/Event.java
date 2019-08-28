package task.tasks;

import error.UnknownCommandException;
import error.task.InvalidArgumentsException;
import error.task.UnknownDateTimeException;
import task.Task;
import util.DukeDateTime;

import java.time.LocalDateTime;
import java.util.Optional;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String arguments) throws UnknownDateTimeException, InvalidArgumentsException {
        super(getDescription(arguments), TaskKeyword.EVENT);
        this.at = getTiming(arguments);
    }

    @Override
    protected String getTaskStringCode() {
        return "E";
    }

    @Override
    protected Optional<String> getTaskExtraDetails() {
        String atString = DukeDateTime.getString(at);
        return Optional.of(String.format("at: %s", atString));
    }

    private static String getDescription(String arguments) {
        return arguments.split(" /at ")[0];
    }

    private LocalDateTime getTiming(String arguments) throws UnknownDateTimeException, InvalidArgumentsException {
        try {
            String dateTime = arguments.split(" /at ")[1];
            return DukeDateTime.parseDateTime(dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentsException("\"at");
        }
    }
}
