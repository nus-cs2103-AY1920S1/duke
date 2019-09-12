import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    String description;
    LocalDateTime date;
    boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns a printable String that represents the state of this Task
     * @return String for display
     */
    public String getStatus() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a formatted String that represents the state of this Task
     * @return String for saving
     */
    public String saveFormat() {
        return (isDone ? "1" : "0") + " " + description;
    }

    LocalDateTime getDate() {
        return date;
    }
}

class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    @Override
    public String getStatus() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    @Override
    public String saveFormat() {
        return "T" + (isDone ? "1" : "0") + " " + description;
    }
}

class Deadline extends Task {
    private String memo;
    private String preposition;

    Deadline(String description, String preposition, String memo) {
        super(description);
        this.memo = memo;
        this.preposition = preposition;
        try {
            // deadline 12 /by 12/12/2012 2359
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            date = LocalDateTime.parse(memo, formatter);
        } catch (DateTimeParseException e) {
            // System.out.println("parse failed");
            // do nothing
        }
    }

    public String getMemo() {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm")) : memo;
//        return date != null ? date.toString() : memo;
    }

    @Override
    public String getStatus() {
        return "[D][" + getStatusIcon() + "] " + description + " (" + preposition + ": " + getMemo() + ")";
    }

    @Override
    public String saveFormat() {
        return "D" + (isDone ? "1" : "0") + " " + description + " /" + preposition + " " + memo;
    }
}

class Event extends Task {
    private String memo;
    private String preposition;

    Event(String description, String preposition, String memo) {
        super(description);
        this.memo = memo;
        this.preposition = preposition;
        try {
            // deadline 12 /by 12/12/2012 2359
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            date = LocalDateTime.parse(memo, formatter);
        } catch (DateTimeParseException e) {
            // System.out.println("parse failed");
            // do nothing
        }
    }

    public String getMemo() {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm")) : memo;
    }

    @Override
    public String getStatus() {
        return "[E][" + getStatusIcon() + "] " + description + " (" + preposition + ": " + getMemo() + ")";
    }

    @Override
    public String saveFormat() {
        return "E" + (isDone ? "1" : "0") + " " + description + " /" + preposition + " " + memo;
    }
}

