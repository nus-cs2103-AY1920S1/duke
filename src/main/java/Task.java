import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    String description;
    boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getStatus() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String saveFormat() {
        return (isDone ? "1" : "0") + " " + description;
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
    private LocalDate date;

    Deadline(String description, String preposition, String memo) {
        super(description);
        this.memo = memo;
        this.preposition = preposition;
        try {
            // deadline 12 /by 12/12/2012 2359
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            date = LocalDate.parse(memo, formatter);
        } catch (DateTimeParseException e){
            System.out.println("parse failed");
            // do nothing
        }
    }

    public String getMemo() {
        return date != null ? date.toString() : memo;
    }

    @Override
    public String getStatus() {
        return "[D][" + getStatusIcon() + "] " + description + " (" + preposition + ": " + memo + ")";
    }
    @Override
    public String saveFormat() {
        return "D" + (isDone ? "1" : "0") + " " + description + " /" + preposition + " " + memo;
    }
}

class Event extends Task {
    private String memo;
    private String preposition;
    private LocalDate date;

    Event(String description, String preposition, String memo) {
        super(description);
        this.memo = memo;
        this.preposition = preposition;
        try {
            // deadline 12 /by 12/12/2012 2359
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            date = LocalDate.parse(memo, formatter);
        } catch (DateTimeParseException e){
            System.out.println("parse failed");
            // do nothing
        }
    }
    public String getMemo() {
        return date != null ? date.toString() : memo;
    }

    @Override
    public String getStatus() {
        return "[E][" + getStatusIcon() + "] " + description + " (" + preposition + ": " + memo + ")";
    }
    @Override
    public String saveFormat() {
        return "E" + (isDone ? "1" : "0") + " " + description + " /" + preposition + " " + memo;
    }
}

