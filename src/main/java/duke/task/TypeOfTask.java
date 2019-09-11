package duke.task;

/**
 * Enum that contains the various types of tasks accepted by Duke
 * e.g.,<code>todo</code>, <code>deadline</code>, <code>event</code>.
 */
public enum TypeOfTask {
    TODO, DEADLINE, EVENT;

    /**
     * Enum represented in <code>String</code>.
     *
     * @return String representing the enum
     */
    @Override
    public String toString() {
        String taskType = super.toString();
        return taskType.substring(0, 1) + taskType.substring(1).toLowerCase();
    }
}
