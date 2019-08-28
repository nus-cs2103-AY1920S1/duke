import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public abstract class Task {
    private String description;
    private boolean isDone;
    protected static DateTimeFormatter outDTF = DateTimeFormatter.ofPattern("MMMM d y, K:mm a");
    protected static DateTimeFormatter fileDTF = DateTimeFormatter.ofPattern("d/M/y'T'HHmm");

    /**
     * Constructs an abstract Task with description.
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

    protected char getStatusIcon() {
        return this.isDone ? '\u2713' : '\u2718'; //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    protected String toFileString() {
        return (char) 31 + (this.isDone ? "1" : "0") + (char) 31 + this.description;
    }

    protected abstract String childClass();


    /**
     * Parses FileString representing Serialised Task into Task.
     *
     * @param str Serialised task
     * @return Task
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

