package jermi.task;

import static jermi.misc.Constant.DEADLINE_STRING_REPRESENTATION;
import static jermi.misc.Constant.DEADLINE_TYPE_CODE;
import static jermi.misc.Constant.TASK_UNDONE_STRING_REPRESENTATION;

import jermi.misc.TaskType;

/**
 * A representation of the deadline task.
 */
public class Deadline extends TaskWithDateTime {
    /**
     * Public constructor for class.
     * Creates an uncompleted deadline task.
     *
     * @param description Description of task.
     * @param deadline Deadline of task.
     */
    public Deadline(String description, String deadline) {
        this(description, deadline, TASK_UNDONE_STRING_REPRESENTATION);
    }

    /**
     * Public constructor for class.
     * Creates a completed/uncompleted deadline task.
     *
     * @param description Description of task.
     * @param deadline Deadline of task.
     * @param isDone "1" for completed task, else "0".
     */
    public Deadline(String description, String deadline, String isDone) {
        super(description, deadline, isDone);
    }

    /**
     * Returns task type code, "D".
     *
     * @return "D".
     */
    @Override
    String getTypeCode() {
        return DEADLINE_TYPE_CODE;
    }

    /**
     * Returns a string representation of task.
     *
     * @return A string representation of task.
     */
    @Override
    public String toString() {
        return String.format(DEADLINE_STRING_REPRESENTATION, super.toString(), this.getDateTime());
    }

}
