import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task object containing task description and task status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private static Ui ui = new Ui();

    /**
     * Constructs a unspecified and unfinished Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * onstructs a unspecified Task object.
     *
     * @param description Description of the task.
     * @param isDone True if the task is finished.
     */
    public Task(String description, Boolean isDone) {
        this.description = description.trim();
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     *
     * @return New finished task.
     */
    public Task finish() {
        return new Task(this.description, true);
    }

    /**
     * Outputs as a String the status of task.
     * Tick for done; Cross for not done.
     *
     * @return String symbol of the status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Converts a String to a Task object.
     *
     * @param s String to be converted.
     * @return New Task object represented by the String.
     */
    public static Task toTask(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy_@_hh:mma");
        Boolean isDone = (s.charAt(4) == '\u2713');
        switch (s.charAt(1)) {
        case 'T':
                return new Todo(s.substring(7), isDone);
        case 'E':
                String description = s.substring(7, s.lastIndexOf('(') - 1);
                String[] times = s.substring(s.lastIndexOf('(') - 1).split(" ");
                LocalDateTime start = LocalDateTime.parse(times[2], formatter);
                LocalDateTime end = LocalDateTime.parse(times[5],formatter);
                return new Event(description, start, end, isDone);
        case 'D':
                String description2 = s.substring(7, s.lastIndexOf('(') - 1);
                String[] deadlines = s.substring(s.lastIndexOf('(') - 1).split(" ");
                LocalDateTime dl = LocalDateTime.parse(deadlines[2],formatter);
                return new Deadline(description2, dl, isDone);
        default:
                return new Task(s.substring(7));
        }
    }

    /**
     * ToString method for printing.
     *
     * @return String representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + "\n";
    }

    //...
}

