package duke.task;

/**
 * Represents the possible types of Tasks.
 * Possible values: TODO, DEADLINE, EVENT.
 */
public enum TaskType {
    DEADLINE("by", "D"),
    EVENT("at", "E"),
    TODO("\n", "T"),
    INVALID(null, null);

    private final String delimiter;
    private final String initial;

    TaskType(String delimiter, String initial) {
        this.delimiter = delimiter;
        this.initial = initial;
    }

    /**
     * Returns the delimiter used to separate the description of a Task from its additional details.
     * Possible usage: in an alternative implementation of AddTaskCommand.
     * @return Delimiter used to separate the description of a Task from its additional details.
     */
    public String getDelimiter() {
        return this.delimiter;
    }

    /**
     * Returns the TaskType of the task, given a String describing its type.
     * @param taskTypeString String describing the type of the Task.
     * @return TaskType of the task.
     */
    public static TaskType getTaskType(String taskTypeString) {
        switch (taskTypeString) {
        case "todo":
            return TaskType.TODO;
        case "event":
            return TaskType.EVENT;
        case "deadline":
            return TaskType.DEADLINE;
        default:
            return TaskType.INVALID;
        }
    }

    /**
     * Returns the initial representing the TaskType.
     * Possible usage: for icons representing the TaskType in UI.
     * @return Initial representing the TaskType.
     */
    public String getTaskTypeInitial() {
        return this.initial;
    }
}
