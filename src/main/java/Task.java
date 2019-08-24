import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

import static java.time.temporal.ChronoField.*;

public abstract class Task {
    private String description;
    private boolean isDone;
    protected static DateTimeFormatter outDTF = DateTimeFormatter.ofPattern("MMMM d y, K:mm a");
    protected static DateTimeFormatter fileDTF = DateTimeFormatter.ofPattern("d/M/y'T'HHmm");

    public Task(String description) throws DukeException {
        this.description = description.trim();
        if (this.description.isBlank())
            throw new EmptyFieldDukeException("description", this.childClass());
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
        return "[" + this.getStatusIcon() + "] " +
                this.description;
    }

    protected String toFileString(){
        return (char) 31 + (this.isDone ? "1" : "0") + (char) 31 +
                this.description;
    };

    abstract protected String childClass();

    public static Task parseFileTask(String str) throws DukeException{
        String[] prop = str.split("\\x1f");
        Task t = null;
        switch (prop[0]){
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
        if(t != null && prop[1].equals("1"))
            t.setDone();
        return t;
    }

    protected static DateTimeFormatter inDTF() {
        LocalDateTime dt = LocalDateTime.now();
        return new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("[MMMM][MMM][ ][/][d][ ][/][MMMM][MMM][M][ ][/][yyyy][ ]['T'][HH[':']mm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.YEAR_OF_ERA, dt.getYear())
                .parseDefaulting(MONTH_OF_YEAR, dt.getMonthValue())
                .parseDefaulting(ChronoField.DAY_OF_MONTH, dt.getDayOfMonth())
                .toFormatter();
    }

    public static Task parseTask(String str) throws DukeException {
        if (str.startsWith("deadline")) {
            String[] temp = str.substring(8).split(" /by ");
            if (temp.length < 1 || temp[0].isBlank())
                throw new EmptyFieldDukeException("description", "deadline");
            if (temp.length < 2 || temp[1].isBlank())
                throw new EmptyFieldDukeException("time", "deadline");
            return new Deadline(temp[0], temp[1]);
        }
        if (str.startsWith("event")) {
            String[] temp = str.substring(5).split(" /at ");
            if (temp.length < 1 || temp[0].isBlank())
                throw new EmptyFieldDukeException("description", "event");
            if (temp.length < 2 || temp[1].isBlank())
                throw new EmptyFieldDukeException("time", "event");
            return new Event(temp[0], temp[1]);
        }
        if (str.startsWith("todo")) {
            return new Todo(str.substring(4));
        }
        return null;
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

    public String toFileString(){
        return "D" + super.toFileString() + (char) 31 + this.by.format(super.fileDTF);
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
    public String toFileString(){
        return "E" + super.toFileString() + (char) 31 + this.at.format(super.fileDTF);
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
    public String toFileString(){
        return "T" + super.toFileString();
    }
}