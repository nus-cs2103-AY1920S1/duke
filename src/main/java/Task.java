public abstract class Task {

    protected boolean completed;
    protected String taskName;

    public Task(String taskName) {
        this.completed = false;
        this.taskName = taskName;
    }

    public boolean markAsComplete() {
        if (!completed) {
            completed = true;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[" + (completed ? "✓" : "✗") + "] " + taskName;
    }

}
