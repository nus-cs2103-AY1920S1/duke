package duke.task;

public abstract class Task {

    Status completed;
    String taskName;

    Task(String taskName) {
        this.completed = Status.INCOMPLETE;
        this.taskName = taskName;
    }

    Task(Status status, String taskName) {
        this.completed = status;
        this.taskName = taskName;
    }

    public boolean markAsComplete() {
        if (this.completed == Status.INCOMPLETE) {
            completed = Status.COMPLETE;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[" + (this.completed == Status.COMPLETE ? "✓" : "✗") + "] " + taskName;
    }

    abstract public String toSaveString();

}
