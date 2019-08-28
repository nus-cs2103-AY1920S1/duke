package duke.parser;

/**
 * Todo task that is with tag T and tasktype of todo.
 * @author Yang Shuting
 * @see Task
 * @see Deadine
 * @see Event
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description.trim());
        super.tag = "[T]";
        super.taskType = TaskType.TODO;
    }
}
