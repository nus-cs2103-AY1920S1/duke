package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private static final Pattern PAT = Pattern.compile("(.+) /by (.+)");
    private static final DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH);
    private static final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm", Locale.ENGLISH);
    protected LocalDateTime by;

    public Deadline(String desc) {
        super(desc);
    }

    public Deadline(String desc, boolean done) {
        super(desc, done);
    }

    /**
     * Constructor method.
     *
     * @param desc Task description
     * @param by Task deadline
     */
    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.by = by;
    }

    /**
     * Constructor method.
     *
     * @param desc Task description
     * @param by Task deadline
     * @param done Task done state
     */
    public Deadline(String desc, LocalDateTime by, boolean done) {
        super(desc, done);
        this.by = by;
    }

    public static Task parse(String commandContent) throws DukeException {
        Matcher matcher = PAT.matcher(commandContent);

        if (!matcher.matches()) {
            throw new DukeException("OOPS!!! Arguments is in wrong format");
        }
        try {
            LocalDateTime dueDate = LocalDateTime.parse(matcher.group(2), readFormatter);
            Task task = new Deadline(matcher.group(1), dueDate);
            return task;
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! The date inputted is not in 'DD/MM/YYYY HHmm' format");
        }
    }

    /**
     * Getter for by variable.
     *
     * @return Date of deadline
     */
    public LocalDateTime getDateBy() {
        return by;
    }

    /**
     * Setter for by variable.
     *
     * @param by Date of deadline
     */
    public void setDateBy(LocalDateTime by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateBy().format(displayFormatter) + ")";
    }
}
