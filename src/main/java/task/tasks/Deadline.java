package task.tasks;

import error.UnknownCommandException;
import error.task.InvalidArgumentsException;
import error.task.UnknownDateTimeException;
import task.Task;
import util.DukeDateTime;

import java.time.LocalDateTime;
import java.util.Optional;

public class Deadline extends Task {
    private LocalDateTime by;

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
            throw new InvalidArgumentsException("/by");
        }
    }
}
