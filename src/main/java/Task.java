import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with description.
     *
     * @param description of the Task.
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
     * Generates the appropriate Task object based on given input from the storage file.
     *
     * @param s the line of String being read from the storage file.
     * @return the Task object based on input.
     */
    public static Task genTaskFromData(String s) {
        Task t;
        String[] cmdList = s.split("\\|");
        assert cmdList.length > 0 : "Blank data loaded into storage file";
        String keyword = cmdList[0].trim();
        Boolean isDone = cmdList[1].trim().equals("1") ? true : false;
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
            } else { // can only be (keyword.equalsIgnoreCase("E"))
                assert (keyword.equalsIgnoreCase("E")) : "Wrongly formatted data was stored into storage file";
                t = new Event(description, date, time);
            }
        }
        if (isDone) {
            t.markAsDone();
        }
        return t;
    }

    /**
     * Stringifies the Task in the format specified when writing to storage file.
     *
     * @return string that is to be written to storage file.
     */
    public String toDataFormat() {
        return (isDone ? "1"
                : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
