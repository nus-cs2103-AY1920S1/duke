package duke.task;

import duke.exception.IllegalDescriptionException;

import java.time.LocalDateTime;

/** A class representing a todo task. */
public class ToDo extends Task {
    /**
     * Constructor of the toDo task.
     * @param description description of the task.
     */
    public ToDo(String description) throws IllegalDescriptionException {
        super(description);
    }

    /**
     * Returns task type.
     * @return task type.
     */
    public TaskType getTaskType() {
        return TaskType.ToDo;
    }

    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    /**
     * Returns a String representation of the task.
     * @return a String representation of the task, consisting of the type of
     *         the task and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

