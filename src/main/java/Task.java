public class Task {

    protected String description;
    protected boolean isDone = false;
    protected String typeOfTask;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTypeOfTask() {
        return this.typeOfTask;
    }
}