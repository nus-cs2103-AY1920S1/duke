package duke.task;

/**
 * Represents a todos task.
 */
public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    public int getDiffDays() {
        return Integer.MAX_VALUE;
    }

    /**
     * Deals with changing the task to file format string.
     *
     * @return task as string.
     */
    public String format() {
        return "T" + super.format();
    }

    /**
     * Deals with changing the task to print format string.
     *
     * @return task as string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
