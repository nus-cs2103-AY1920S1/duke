package duke.task;

import java.lang.String;

/**
 * Encapsulates a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with description, marked as not done by default.
     *
     * @param description  Description of task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Retrieves the status icon representing status of task completion.
     *
     * @return Status icon representing status of task completion.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    /**
     * Retrieves a number representing status of task completion.
     *
     * @return Number representing status of task completion.
     */
    public String getStatus() {
        return (isDone ? "1" : "0"); // return 1 or 0
    }

    /**
     * Checks whether task description contains keyword.
     *
     * @return True if task description contains keyword, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Converts the task in String format to a Task object.
     *
     * @return The task as a Task object.
     */
    public static Task convertStringToTask(String line) {
        String[] parameters = line.split("\\|");
        // To-Do
        if (line.charAt(0) == 'T') {
            Task task = new ToDo(parameters[2].trim());
            if (parameters[1].trim().equals("1")) {
                task.markAsDone();
            }
            return task;
        } else if (line.charAt(0) == 'D') { // Deadline
            Task task = new Deadline(parameters[2].trim(), parameters[3].trim());
            if (parameters[1].trim().equals("1")) {
                task.markAsDone();
            }
            return task;
        } else if (line.charAt(0) == 'E') { // Event
            Task task = new Event(parameters[2].trim(), parameters[3].trim());
            if (parameters[1].trim().equals("1")) {
                task.markAsDone();
            }
            return task;
        }
        assert false;
        return null;
    }

    /**
     * Converts the task to String format to write to hard disk.
     *
     * @return Task in String format.
     */
    public String convertTaskToString() {
        return String.format("- | %s | %s", getStatus(), description);
    }

    /**
     * Converts the task to String format for output.
     *
     * @return Task in String format.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
