public class Task {
    protected String description;
    protected boolean isDone;
    public static int total = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        total++;
    }

    public String getStatusIcon() {
        if(isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
