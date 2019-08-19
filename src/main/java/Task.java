public class Task {
    protected String description;
    protected boolean isDone;
    String typeOfTask = "";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        String statusIcon = this.getStatusIcon();
        return  ("[" + typeOfTask + "]" + "[" + statusIcon + "] "
                + description);
    }
}
