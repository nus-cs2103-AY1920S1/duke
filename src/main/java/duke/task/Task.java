package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task.
 */
public class Task {

    String description;
    private boolean isDone;

    /**
     * Initiates a Task object.
     * @param description content of a task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean flag) {
        this.isDone = flag;
    }

    /**
     * Initiates a Task object.
     * @param description content of a task
     * @param isDone mark if the task is already done
     */
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private Date parseDate(String input) throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date t = new Date();
        t = ft.parse(input);
        return t;
    }

    String formatDescription() throws ParseException {
        String item = description.substring(0, description.indexOf("/"));
        String tag = description.substring(description.indexOf("/") + 1);
        String prep = tag.substring(0, tag.indexOf(" "));
        String time = tag.substring(tag.indexOf(" ") + 1);
        Date date = parseDate(time);
        return item + "(" + prep + ": " + date + ")";
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Expresses a Task object in natural language.
     * @return string representation of a task
     * @throws ParseException if description cannot be parsed
     */
    public String repr() throws ParseException {
        return "[" + getStatusIcon() + "] " + description;
    }
}
