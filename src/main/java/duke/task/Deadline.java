package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task implements UsingDateTime {
    private static final Pattern PAT = Pattern.compile("(.+) /by (.+)");
    protected LocalDateTime by;

    /**
     * Constructor method.
     *
     * @param desc Task description
     * @param by Task deadline
     * @param done Task done state
     */
    public Deadline(String desc, LocalDateTime by, boolean... done) {
        super(desc);
        this.by = by;

        if (done.length == 1) {
            setDone(done[0]);
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

    /**
     * Parses user input and look for Deadline object.
     *
     * @param commandContent Input string
     * @return Deadline object
     */
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
     *  Parses stored data string and look for Deadline object.
     *
     * @param input Data string from save file.
     * @return Deadline object
     */
    public static Deadline parseFromData(String input, boolean... done) throws DukeException {
        String[] inputs = input.split(gap);
        try {
            LocalDateTime dateBy = LocalDateTime.parse(inputs[3]);
            Deadline deadline = new Deadline(inputs[2], dateBy);
            if (done.length == 1) {
                deadline.setDone(done[0]);
            }
            return deadline;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid data");
        }
    }

    @Override
    public String toStorageString() {
        return "D" + gap + super.toStorageString() + gap + getDateBy();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateBy().format(displayFormatter) + ")";
    }
}
