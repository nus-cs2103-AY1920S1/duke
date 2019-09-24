package tasks;

/**
 * Encapsulates the Task object.
 * The Task object includes its own description
 * as well as if any of the object instantiated is done or uncompleted.
 * Also, it keep tracks of a counter of all Task objects that are instantiated.
 * The Task object provides the extension to other children classes such as Todo, Deadline and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    String typeOfTask;

    /**
     * Constructs a Task object with a specific description of a task that has to be completed.
     *
     * @param description This is a brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the Task with a tick or cross icon if the task is done or incomplete respectively.
     *
     * @return a tick or cross icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the Task object as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get the type of task. This is a getter method.
     *
     * @return The type of task.
     */
    public String getTypeOfTask() {
        return this.typeOfTask;
    }

    /**
     * Provides the description of the task. This is a getter.
     *
     * @return The description of the task object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is completed.
     *
     * @return Boolean that indicates if the task is done.
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Convert to standard string format
     *
     * @return a message that reflects the type and description of the Task.
     */
    public String toString() {
        String statusIcon = this.getStatusIcon();
        return ("[" + typeOfTask + "]" + "[" + statusIcon + "] " + description);
    }

    public boolean equals(Task taskOfComparison) {
        if (this == taskOfComparison) {
            return true;
        }
        if (taskOfComparison == null || getClass() != taskOfComparison.getClass()) {
            return false;
        }
        Task task = taskOfComparison;
        if (this.description.equals(task.getDescription())
                && this.typeOfTask.equals(task.getTypeOfTask())) {
            return true;
        }
        return false;
    }
}
