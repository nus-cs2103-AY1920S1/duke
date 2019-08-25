package duke.task;

public enum TaskType {
    todo(false),
    deadline(true),
    event(true);

    private boolean hasTime;

    TaskType(boolean hasTime) {
        this.hasTime = hasTime;
    }

    public boolean hasTime() {
        return this.hasTime;
    }
}