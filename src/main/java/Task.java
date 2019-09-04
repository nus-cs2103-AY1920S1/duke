import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task Constructor.
     * @param description is the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * genTaskFromData() will generate different Task based on given input from file data.
     *
     * @param s is the input String from file data
     * @return identified task
     */
    public static Task genTaskFromData(String s) {
        Task t;
        String[] cmdList = s.split("\\|");
        String keyword = cmdList[0].trim();
        Boolean isDone = Boolean.valueOf(cmdList[1].trim());
        String description = cmdList[2].trim();
        if (keyword.equalsIgnoreCase("T")) {
            t = new Todo(description);
        } else {
            String dateAndTime = cmdList[3].trim();
            String[] dateTime = dateAndTime.split("\\s*,");
            LocalDate date = LocalDate.parse(dateTime[0].trim(), DateTimeFormatter.ofPattern("d/MM/yyy"));
            LocalTime time = LocalTime.parse(dateTime[1].trim(), DateTimeFormatter.ofPattern("HHmm"));
            if (keyword.equalsIgnoreCase("D")) {
                t = new Deadline(description, date, time);
            } else { //(keyword.equalsIgnoreCase("E"))
                t = new Event(description, date, time);
            }
        }
        if (isDone) {
            t.markAsDone();
        }
        return t;
    }

    public String toDataFormat() {
        return (isDone ? "1"
                : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
