package duke.object;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a user's task
 */
public class Task {
    private String description;
    private String details;
    private LocalDateTime dateTime;
    private char type;
    private boolean isDone;
    private int recur;

    public Task(char type, String description) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public Task(char type, String description, String details) {
        assert details.matches("(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d) (([0-1][0-9]|2[0-4])[0-5][0-9])") : " Invalid DateTime Format";
        this.description = description;
        this.type = type;
        this.details = details;
        this.dateTime = LocalDateTime.parse(details, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        this.isDone = false;
    }

    public Task(char type, boolean done, String description) {
        this.description = description;
        this.type = type;
        this.isDone = done;
    }

    public Task(char type, boolean done, String description, String details) {
        this.description = description;
        this.type = type;
        this.isDone = done;
        this.details = details;
    }

    private String getStatusIcon() {
        return (isDone ? "O" : "X"); //return tick or X symbols
    }

    public Task setDone() {
        this.isDone = true;
        return this;
    }

    public int getRecur() {
        return this.recur;
    }

    public Task setRecur(int frequency) {
        if (this.dateTime == null) {
            throw new DukeException("Invalid Task for recursion");
        } else {
            this.recur = frequency;
            return this;
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String getDetails() {
        return this.details;
    }

    public char getType () {
        return this.type;
    }

    public boolean isDone () {
        return this.isDone;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String toString() {
        if (this.details == null) {
            return String.format("[%c][%s] %s", this.type, this.getStatusIcon(), this.description);
        } else if (this.type == 'E') {
            return String.format("[%c][%s] %s at %s", this.type, this.getStatusIcon(), this.description, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm").format(this.dateTime));
        } else {
            return String.format("[%c][%s] %s by %s", this.type, this.getStatusIcon(), this.description, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm").format(this.dateTime));
        }
    }
}