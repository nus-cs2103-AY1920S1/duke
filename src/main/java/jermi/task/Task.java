package jermi.task;

import static jermi.misc.Constant.TASK_DONE_ICON;
import static jermi.misc.Constant.TASK_DONE_STRING_REPRESENTATION;
import static jermi.misc.Constant.TASK_SAVE_FORMAT;
import static jermi.misc.Constant.TASK_STRING_REPRESENTATION;
import static jermi.misc.Constant.TASK_UNDONE_ICON;
import static jermi.misc.Constant.TASK_UNDONE_STRING_REPRESENTATION;

/**
 * Base class for task.
 */
public abstract class Task {
    /** Description of the task. */
    private String description;
    /** State of the task: true if completed, else false. */
    private boolean isDone;

    /** Constructor for class. */
    Task(String description, String isDone) {
        this.description = description;
        this.isDone = isDone.equals(TASK_DONE_STRING_REPRESENTATION);
    }

    /**
     * Returns a status icon.
     *
     * @return Tick if task is completed, else cross.
     */
    private String getStatusIcon() {
        return this.isDone ? TASK_DONE_ICON : TASK_UNDONE_ICON;
    }

    /**
     * Sets status of task to completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a task type code.
     *
     * @return "T", "D" or "E" depending on task type.
     */
    abstract String getTypeCode();

    /**
     * Returns a string representation of task.
     *
     * @return A string representation of task.
     */
    @Override
    public String toString() {
        return String.format(TASK_STRING_REPRESENTATION, this.getTypeCode(), this.getStatusIcon(), this.description);
    }

    /**
     * Converts the task into a string in save format.
     *
     * @return A string in save format.
     */
    public String toSaveFormat() {
        return String.format(TASK_SAVE_FORMAT, this.getTypeCode(),
                this.isDone ? TASK_DONE_STRING_REPRESENTATION : TASK_UNDONE_STRING_REPRESENTATION, this.description);
    }

    /**
     * Checks if the task contains one or more of the keywords.
     *
     * @param keywords Keywords.
     * @return {@code true} if task contains one or more of the keywords, else {@code false}.
     */
    public boolean contains(String... keywords) {
        for (String keyword : keywords) {
            if (this.toString().contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
