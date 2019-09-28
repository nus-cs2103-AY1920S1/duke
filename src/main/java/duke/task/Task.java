package duke.task;

import duke.util.DukeException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Task is the base base class for all tasks. Basically, a Task object encapsulates the information about the
 * description and status (done or not).
 */
public class Task {

    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy, HH:mm");
    protected String description;
    protected boolean isDone;

    /**
     * This is a class constructor specifying the description for a task. The <code>isDone</code> status is set to be
     * <code>false</code>.
     *
     * @param description a string containing the detail for this task
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This is a class constructor specifying the description for a task and whether the task is done.
     *
     * @param description a string containing the detail for this task
     * @param isDone      a boolean indicating whether this task is done
     */
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "μ2713" : "μ2718");
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    /**
     * Creates a specific <code>Task</code> object from a string, which contains the information about a task stored in
     * the hard disk. Task types can be <code>Todo</code>, <code>Deadline</code>, and <code>Event</code>, indicated by
     * the first letter of the stored string value.
     *
     * @param taskInfo a string in a certain format that stores information about a task in the hard disk
     * @return a specific <code>Task</code> object, namely, <code>Todo</code>, <code>Deadline</code>, or
     * <code>Event</code>,
     */
    public static Task from(String taskInfo) throws DukeException {
        String[] taskInfos = taskInfo.split("\\|");
        switch (taskInfos[0]) {
        case "T":
            assert taskInfos.length == 3;
            return new Todo(taskInfos[2], Boolean.parseBoolean(taskInfos[1]));
        case "D":
            assert taskInfos.length == 4;
            return new Deadline(taskInfos[2], LocalDateTime.parse(taskInfos[3]), Boolean.parseBoolean(taskInfos[1]));
        case "E":
            assert taskInfos.length == 4;
            return new Event(taskInfos[2], LocalDateTime.parse(taskInfos[3]), LocalTime.parse(taskInfos[4]),
                    Boolean.parseBoolean(taskInfos[1]));
        default:
            throw new DukeException("Oh! Storage is corrupted!");
        }
    }

    public void markDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of a task.
     *
     * @return a string representation of a task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
