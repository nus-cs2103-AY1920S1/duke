package duke.command.find.predicates;

import duke.model.Deadline;
import duke.model.Event;
import duke.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class TaskDateBasedPredicateFactory implements FindTaskCleanableCommandPredicateFactory {
    private static final String DATE_TIME_REGEX = "\\d{1,2}/\\d{1,2}/\\d{4}(?: \\d{4})?";
    private static final DateTimeFormatter DATE_TIME_PARSER = DateTimeFormatter.ofPattern("d/M/yyyy[ HHmm]");
    private Pattern commandMatcher;

    protected TaskDateBasedPredicateFactory(String commandKeyword) {
        final String optionalSpace = " ?";

        final StringBuilder patternBuilder = new StringBuilder(optionalSpace);
        patternBuilder.append(Pattern.quote(commandKeyword));
        patternBuilder.append(" ");
        patternBuilder.append("(?<datetime>" + DATE_TIME_REGEX + ")");
        patternBuilder.append(optionalSpace);

        commandMatcher = Pattern.compile(patternBuilder.toString());
    }

    protected static LocalDateTime getTaskDate(Task task) {
        if (task instanceof Event) {
            final Event event = (Event) task;
            return event.getEventDateTime();
        } else if (task instanceof Deadline) {
            final Deadline deadline = (Deadline) task;
            return deadline.getDeadline();
        }

        return null;
    }

    @Override
    public boolean shouldEnable(String command) {
        return commandMatcher.matcher(command).find();
    }

    protected TemporalAccessor extractDateTimeArgument(String command) {
        final Matcher m = commandMatcher.matcher(command);
        m.find();
        final String commandDateTime = m.group("datetime");

        return DATE_TIME_PARSER.parseBest(commandDateTime, LocalDateTime::from, LocalDate::from);
    }

    protected LocalDateTime extractDateTimeArgument(
            String command, Function<LocalDate, LocalDateTime> toLocalDateTimeFn) {
        final TemporalAccessor partialDateTime = extractDateTimeArgument(command);
        if (partialDateTime instanceof LocalDateTime) {
            return (LocalDateTime) partialDateTime;
        } else if (partialDateTime instanceof LocalDate) {
            return toLocalDateTimeFn.apply((LocalDate) partialDateTime);
        } else {
            return null;
        }
    }

    @Override
    public String cleanCommand(String command) {
        return commandMatcher.matcher(command).replaceAll("");
    }
}
