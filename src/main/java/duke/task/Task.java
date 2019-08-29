package duke.task;

import duke.exception.CorruptedFileDukeException;
import duke.exception.DukeException;
import duke.exception.EmptyFieldDukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * DateTimeFormatter meant for duke.Ui output.
     */
    protected static DateTimeFormatter outDTF = DateTimeFormatter.ofPattern("MMMM d y, K:mm a");


    /**
     * DateTimeFormatter meant for File output.
     */
    protected static DateTimeFormatter fileDTF = DateTimeFormatter.ofPattern("d/M/y'T'HHmm");


    /**
     * Constructs an abstract duke.task.Task with description.
     *
     * @param description Description
     * @throws EmptyFieldDukeException On empty description
     */
    public Task(String description) throws EmptyFieldDukeException {
        this.description = description.trim();
        if (this.description.isBlank()) {
            throw new EmptyFieldDukeException("description", this.childClass());
        }
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return this.isDone ? "✓" : "✘";
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFileString() {
        return (char) 31 + (this.isDone ? "1" : "0") + (char) 31 + this.description;
    }

    protected abstract String childClass();


    /**
     * Parses FileString representing Serialised duke.task.Task into duke.task.Task.
     *
     * @param str Serialised task to be parsed
     * @return duke.task.Task as parsed
     * @throws DukeException On parsing problem
     */
    public static Task parseFileTask(String str) throws DukeException {
        String[] prop = str.split("\\x1f");
        Task t;
        switch (prop[0]) {
        case "D":
            t = new Deadline(prop[2], prop[3]);
            break;
        case "E":
            t = new Event(prop[2], prop[3]);
            break;
        case "T":
            t = new Todo(prop[2]);
            break;
        default:
            throw new CorruptedFileDukeException();
        }
        if (prop[1].equals("1")) {
            t.setDone();
        }
        return t;
    }

    protected static DateTimeFormatter inDateTimeFormat() {
        LocalDateTime dt = LocalDateTime.now();
        return new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("[MMMM][MMM][ ][/][d][ ][/][MMMM][MMM][M][ ][/][yyyy][ ]['T'][HH[':']mm]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, dt.getHour())
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, dt.getMinute())
            .parseDefaulting(ChronoField.YEAR_OF_ERA, dt.getYear())
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, dt.getMonthValue())
            .parseDefaulting(ChronoField.DAY_OF_MONTH, dt.getDayOfMonth())
            .toFormatter();
    }
}
