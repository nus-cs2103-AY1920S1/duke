package duke.task;

/**
 * Set of possible types of tasks.
 */
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    /**
     * Override implementation of toString that returns the lower case version.
     *
     * @return Lower case string representation of enum.
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}