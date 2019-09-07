package duke.task;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * abstract class Task.
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime pattern;
    public DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");


    public Task(String description) {
        this.description = perfectDescription(description);
        this.isDone = false;
    }

    /**
     * Task(String description, String done).
     */
    public Task(String description, String done) {
        this.description = perfectDescription(description);
        if (done.trim().equals("1")) {
            this.isDone = true;
        }
    }

    public String getDescription(){
        return this.description;
    }
    /**
     * perfectDescription.
     */
    public String perfectDescription(String description) {
        String[] temp = description.split(" ");
        String result = "";
        for (String str : temp) {
            if (!str.equals("")) {
                result += " " + str.trim();
            }
        }
        return result.trim();
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "V" : "X"); //return tick or X symbols
    }

    public abstract String getFormatToFile();

    public String getTime() {
        return pattern.format(formatter2);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}