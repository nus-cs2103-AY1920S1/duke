package jermi.task;

import static jermi.misc.Constant.TASK_UNDONE_STRING_REPRESENTATION;
import static jermi.misc.Constant.TO_DO_TYPE_CODE;

import jermi.misc.TaskType;

/**
 * A representation of the todo task.
 */
public class ToDo extends Task {
    /**
     * Public constructor for class.
     * Creates an uncompleted todo task.
     *
     * @param description Description of todo.
     */
    public ToDo(String description) {
        this(description, TASK_UNDONE_STRING_REPRESENTATION);
    }

    /**
     * Public constructor for class.
     * Creates a completed/uncompleted todo task.
     *
     * @param description Description of todo.
     * @param isDone "1" for completed task, else "0".
     */
    public ToDo(String description, String isDone) {
        super(description, isDone);
    }

    /**
     * Returns task type code, "T".
     *
     * @return "T".
     */
    @Override
    String getTypeCode() {
        return TO_DO_TYPE_CODE;
    }
}
