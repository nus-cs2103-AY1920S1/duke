public abstract class Task {

    static int noOfTasks = 1;
    protected int taskID;
    protected boolean completed;
    protected String taskName;

    public Task(String taskName) {
        taskID = noOfTasks;
        this.completed = false;
        this.taskName = taskName;
        noOfTasks++;
    }

    public boolean markAsComplete() {
        if (!completed) {
            completed = true;
            return true;
        } else {
            return false;
        }
    }

    static String totalNoOfTasks() {
        return "\t Now you have " + (noOfTasks - 1) + ((noOfTasks - 1) == 1? " task" : " tasks") + " in the list.\n";
    }

    @Override
    public String toString() {
        return taskID + ".[" + (completed ? "✓" : "✗") + "] " + taskName;
    }

    public String toStringNoID() {
        return "[" + (completed ? "✓" : "✗") + "] " + taskName;
    }
}
