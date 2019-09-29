package duke.task;

public enum TaskPriority {
    LOW,
    MEDIUM,
    HIGH;

    /**
     * Returns the TaskPriority, given a String describing its priority.
     * @param taskPriorityString String describing the priority.
     * @return TaskPriority, based on String provided.
     */
    public static TaskPriority getTaskPriority(String taskPriorityString) {
        switch (taskPriorityString) {
        case "low":
            return TaskPriority.LOW;
        case "medium":
            return TaskPriority.MEDIUM;
        case "high":
            return TaskPriority.HIGH;
        default:
            return null;
        }
    }
}
