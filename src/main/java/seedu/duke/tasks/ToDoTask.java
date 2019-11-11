package seedu.duke.tasks;

import java.util.Date;

public class ToDoTask extends Task {
    /**
     * Constructor for <code>ToDoTask</code> that represents one of the possible task types.
     *
     * @param taskID      ID of the task
     * @param description Description of the <code>ToDoTask</code> task.
     * @param isDone      A boolean that indicates whether a <code>ToDoTask</code> task has been done or not.
     * @param createdAt   Creation time
     * @param updatedAt   Last update time
     */
    ToDoTask(long taskID, boolean isDone, Date createdAt, Date updatedAt, String description) {
        super(taskID, TaskType.TODO, isDone, createdAt, updatedAt, description);
    }

    /**
     * Convenient constructor for <code>ToDoTask</code> that represents one of the possible task types.
     *
     * @param description Description of the <code>ToDoTask</code> task.
     */
    public ToDoTask(String description) {
        super(TaskType.TODO, description);
    }
}
