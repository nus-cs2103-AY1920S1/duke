package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private static final Pattern PAT = Pattern.compile("(.+) /at (.+)  (.+)");
    private static final DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH);
    private static final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm", Locale.ENGLISH);
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Constructor method.
     *
     * @param desc Event description
     * @param startDate Event start datetime
     * @param endDate Event end datetime
     */
    public Event(String desc, LocalDateTime startDate, LocalDateTime endDate) throws DukeException {
        super(desc);
        if (startDate.isAfter(endDate)) {
            throw new DukeException("OOPS!!! StartDate > EndDate");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructor method.
     *
     * @param desc Event description
     * @param startDate Event start datetime
     * @param endDate Event end datetime
     * @param done Event done state
     */
    public Event(String desc, LocalDateTime startDate, LocalDateTime endDate, boolean done) {
        super(desc, done);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Parses user input and look for Event object.
     *
     * @param commandContent Input string
     * @return Event object
     */
    public static Task parse(String commandContent) throws DukeException {
        Matcher matcher = PAT.matcher(commandContent);

        if (!matcher.matches()) {
            throw new DukeException("OOPS!!! Arguments is in wrong format");
        }

        try {
            LocalDateTime startDate = LocalDateTime.parse(matcher.group(2), readFormatter);
            LocalDateTime endDate = LocalDateTime.parse(matcher.group(3), readFormatter);
            return new Event(matcher.group(1), startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! The date inputted is not in 'DD/MM/YYYY HHmm' format");
        }
    }

    /**
     *  Parses stored data string and look for Event object.
     *
     * @param input Data string from save file.
     * @return Event object
     */
    public static Event parseFromData(String input) throws DukeException {
        try {
            String[] inputs = input.split(gap);
            LocalDateTime startDate = LocalDateTime.parse(inputs[3]);
            LocalDateTime endDate = LocalDateTime.parse(inputs[4]);
            return new Event(inputs[2], startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid data");
        }
    }

    /**
     *  Parses stored data string and look for Event object.
     *
     * @param input Data string from save file.
     * @param isDone Whether event task is done.
     * @return Event object
     */
    public static Event parseFromData(String input, boolean isDone) throws DukeException {
        Event event = parseFromData(input);
        event.setDone(isDone);
        return event;
    }

    /**
     * Getter for startDate variable.
     *
     * @return Start date of event.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Setter for startDate variable.
     *
     * @param startDate Start date of event.
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for endDate variable.
     *
     * @return End date of event.
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Setter for endDate variable.
     *
     * @param endDate End date of event.
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toStorageString() {
        return "E" + gap + super.toStorageString() + gap + getStartDate() + gap + getEndDate();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                this.getStartDate().format(displayFormatter) + " to " +
                this.getEndDate().format(displayFormatter) + ")";
    }
}
