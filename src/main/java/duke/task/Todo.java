package duke.task;

/**
 * Todo task that is with tag T and tasktype of todo.
 * @author Yang Shuting
 * @see Task
 * @see Deadline
 * @see Event
 */
public class Todo extends Task {
    /**
     * constructs a Todo task.
     * @param description detail of the task
     */
    public Todo(String description) {
        super(description.trim());
        super.tag = "[T]";
        super.taskType = TaskType.TODO;
    }
}
