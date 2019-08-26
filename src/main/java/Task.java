import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import static java.time.temporal.ChronoField.*;

public abstract class Task {
    private String description;
    private boolean isDone;


    /**
     * DateTimeFormatter meant for Ui output.
     */
    protected static DateTimeFormatter outDTF = DateTimeFormatter.ofPattern("MMMM d y, K:mm a");


    /**
     * DateTimeFormatter meant for File output.
     */
    protected static DateTimeFormatter fileDTF = DateTimeFormatter.ofPattern("d/M/y'T'HHmm");


    /**
     * @param description Initialise General Task
     * @throws EmptyFieldDukeException
     */
    public Task(String description) throws EmptyFieldDukeException {
        this.description = description.trim();
        if (this.description.isBlank())
            throw new EmptyFieldDukeException("description", this.childClass());
        this.isDone = false;
    }


    /**
     * @return Gets done status icon character
     */
    protected char getStatusIcon() {
        return this.isDone ? '\u2713' : '\u2718';
    }


    /**
     * Mark Task as Done.
     */
    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " +
                this.description;
    }


    /**
     * Gets serialized Task.
     *
     * @return String from serializing Task
     */
    protected String serialized() {
        return (char) 31 + (this.isDone ? "1" : "0") + (char) 31 +
                this.description;
    }


    /**
     * @return name of childClass as String
     */
    abstract protected String childClass();

    /**
     * Parse Serialised Task into Task objects
     *
     * @param str serialised task to be parsed
     * @return Task as parsed
     * @throws DukeException if parsing goes wrong
     */
    public static Task parseFileTask(String str) throws DukeException {
        String[] prop = str.split("\\x1f");
        Task t = null;
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
        }
        if (t != null && prop[1].equals("1"))
            t.setDone();
        return t;
    }


    /**
     * @return DateTimeFormatter with current system time as default
     */
    protected static DateTimeFormatter inDTF() {
        LocalDateTime dt = LocalDateTime.now();
        return new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("[MMMM][MMM][ ][/][d][ ][/][MMMM][MMM][M][ ][/][yyyy][ ]['T'][HH[':']mm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, dt.getHour())
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, dt.getMinute())
                .parseDefaulting(ChronoField.YEAR_OF_ERA, dt.getYear())
                .parseDefaulting(MONTH_OF_YEAR, dt.getMonthValue())
                .parseDefaulting(ChronoField.DAY_OF_MONTH, dt.getDayOfMonth())
                .toFormatter();
    }
}

class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by.trim(), super.inDTF());
        } catch (DateTimeParseException e) {
            throw new DateTimeParseDukeException();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(super.outDTF) + ")\n";
    }

    protected String childClass() {
        return "deadline";
    }

    @Override
    public String serialized() {
        return "D" + super.serialized() + (char) 31 + this.by.format(super.fileDTF);
    }
}

class Event extends Task {

    private LocalDateTime at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDateTime.parse(at.trim(), super.inDTF());
        } catch (DateTimeParseException e) {
            throw new DateTimeParseDukeException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(super.outDTF) + ")\n";
    }

    protected String childClass() {
        return "event";
    }

    @Override
    public String serialized() {
        return "E" + super.serialized() + (char) 31 + this.at.format(super.fileDTF);
    }
}

class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + '\n';
    }

    protected String childClass() {
        return "todo";
    }

    @Override
    public String serialized() {
        return "T" + super.serialized();
    }
}