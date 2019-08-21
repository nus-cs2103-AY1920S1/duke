public class Task {
    protected  String description;
    protected boolean isDone;
    protected String tag = "";
    protected String information = "";
    protected static int noOfTask = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        noOfTask++;

    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public static int getNoOfTask() {
        return noOfTask;
    }

    @Override
    public String toString() {
            return tag + "[" + getStatusIcon() + "]"
                    + description + information;
        }
}
