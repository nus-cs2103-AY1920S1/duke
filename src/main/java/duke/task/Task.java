package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task object containing task description and task status.
 */
public class Task implements Comparable<Task> {
    private String description;
    private boolean isDone;

    private static final String TICK = "v";
    private static final String CROSS = "x";

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
     * Constructs a unspecified Task object.
     *
     * @param description Description of the task.
     * @param isDone      True if the task is finished.
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
     * Returns the a LocalDateTime object associated with this task.
     * Starting Time for an Event object. Deadline for a Class Deadline} object.
     * Max value for a Class Todo object.
     *
     * @return LocalDateTime object associated with this task.
     */
    protected LocalDateTime getTime() {
        return LocalDateTime.MAX;
    }

    /**
     * Compares this task to another task. Comparison is based on the ordering of the time at which the task
     * is due to take place.
     * Todo task has the lowest priority.
     *
     * @param other The other task to compare to, not null
     * @return Difference in the time of the two tasks, if applicable.
     */
    @Override
    public int compareTo(Task other) {
        return this.getTime().compareTo(other.getTime());
    }

    /**
     * Outputs the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is finished.
     *
     * @return true if the task is finished.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Outputs as a String the status of task.
     * Tick for done; Cross for not done.
     *
     * @return String symbol of the status of task.
     */
    String getStatusIcon() {
        return (isDone ? TICK : CROSS); //return tick or X symbols
    }

    /**
     * Converts a String to a Task object.
     *
     * @param s String to be converted.
     * @return New Task object represented by the String.
     */
    public static Task toTask(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy_@_hh:mma");
        boolean isDone = (String.valueOf(s.charAt(4)).equals(TICK));
        switch (s.charAt(1)) {
        case 'T':
            return isDone ? new Todo(s.substring(7)).finish() : new Todo(s.substring(7));
        case 'E':
            String description = s.substring(7, s.lastIndexOf('(') - 1);
            String[] times = s.substring(s.lastIndexOf('(') - 1).split(" ");
            LocalDateTime start = LocalDateTime.parse(times[2], formatter);
            LocalDateTime end = LocalDateTime.parse(times[5], formatter);
            Event e = new Event(description, start, end);
            return isDone ? e.finish() : e;
        case 'D':
            String description2 = s.substring(7, s.lastIndexOf('(') - 1);
            String[] deadlines = s.substring(s.lastIndexOf('(') - 1).split(" ");
            LocalDateTime dl = LocalDateTime.parse(deadlines[2], formatter);
            Deadline d = new Deadline(description2, dl);
            return isDone ? d.finish() : d;
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

