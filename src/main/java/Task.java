public abstract class Task {

    protected Status completed;
    protected String taskName;

    public Task(String taskName) {
        this.completed = Status.INCOMPLETE;
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

}
