import java.text.ParseException;
import java.util.Date;

/**
 * Represents a task which could be something to do, an event or a deadline.
 */

public class Task {
    protected String description;
    protected boolean isDone;
    protected Date date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setDate(Date newDate) {
        this.date = newDate;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * A method to write a task into a file according to the format that it can read.
     * @return Returns the string format of a task to write into the designated file.
     */

    public String print() {
        return "";
    }
}