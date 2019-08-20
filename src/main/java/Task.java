public class Task {
    protected String description;
    protected boolean isDone;
    String typeOfTask = "";
    protected static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        if (!description.equals("")) {
            taskCount += 1;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public String toString() {
        String statusIcon = this.getStatusIcon();
        return  ("[" + typeOfTask + "]" + "[" + statusIcon + "] "
                + description);
    }
}
