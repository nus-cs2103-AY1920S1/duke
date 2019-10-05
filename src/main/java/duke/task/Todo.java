package duke.task;

/**
 * Inherits from abstract Task class.
 * Represents a Duke <code>todo</code>
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param taskName    A String which represents the taskName
     *                    e.g., <code>todo borrow books</code>
     *                    where the taskName is "borrow books"
     * @param isCompleted A boolean indicating if the task is completed
     */
    public Todo(String taskName, boolean isCompleted, String tag) {
        super(taskName, isCompleted, tag);
        this.taskType = TypeOfTask.TODO;
        this.details = "";
    }


}
