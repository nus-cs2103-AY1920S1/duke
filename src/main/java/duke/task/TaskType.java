package duke.task;

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

    public String getDelimiter() {
        return this.delimiter;
    }

    public TaskType getTaskType(String taskTypeString) {
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

    public String getTaskTypeInitial() {
        return this.initial;
    }
}
