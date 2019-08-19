public class Task {
    private int taskID;
    private String taskName;

    public Task(int taskID, String taskName) {
        this.taskID = taskID;
        this.taskName = taskName;
    }


    @Override
    public String toString() {
        return taskID + ". " + taskName;
    }
}
