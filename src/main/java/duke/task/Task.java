package duke.task;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Represents a Task that contains the fields description that describes the
 * task, isDone that indicates if a task has been done and typeOfTask that
 * differentiates the type of task which can be an Event task, Todo task, or
 * Deadline task.
 */
public class Task {

    protected String description;
    protected boolean isDone = false;
    protected String typeOfTask;

    /**
     *  Constructs a Task object with specified description.
     * @param description Description that describes the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Get status icon. If a task is done, returns check mark,
     * otherwise, cross mark.
     * @return string of a check or cross mark.
     */
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    /**
     * Change the field isDone to be true.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Check whether is task is done.
     * @return the boolean value isDone.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Get the description of the Task.
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the type of the Task.
     * @return the type of the task.
     */
    public String getTypeOfTask() {
        return this.typeOfTask;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return task.getDescription().equals(this.description);
    }

    public int hashCode() {
        return Objects.hash(this.description);
    }
}