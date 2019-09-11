package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Task is the base base class for all tasks. A Task object encapsulates the information about the description and
 * status (done or not). It is possible to create specific task objects from strings in the storage using
 * <code>from</code>.
 *
 * @author Zhnag Xiaoyu
 */
public class Task {

    static protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy, HH:mm");
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

    /**
     * Creates a specific <code>Task</code> object from a string, which contains the information about a task stored in
     * the hard disk. Task types can be <code>Todo</code>, <code>Deadline</code>, and <code>Event</code>, indicated by
     * the first letter of the stored string value.
     *
     * @param taskInfo a string in a certain format that stores information about a task in the hard disk
     * @return a specific <code>Task</code> object, namely, <code>Todo</code>, <code>Deadline</code>, or
     * <code>Event</code>,
     */
    static public Task from(String taskInfo) {
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
            assert false;
        }
        return new Task(taskInfos[2]);
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
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

    /**
     * Compares two <code>Task</code> objects by their descriptions and <code>isDone</code> status.
     *
     * @param obj the object to be compared
     * @return <code>true</code> if the specifications for two tasks are all the same;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task another = (Task) obj;
            return this.description.equals(another.description) && this.isDone == another.isDone;
        } else {
            return false;
        }
    }
}
