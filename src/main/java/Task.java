public abstract class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public Task(String isDone, String description) {
        int a = Integer.parseInt(isDone.trim());
        this.isDone = a == 0 ? true : false;
        this.description = description;
    }


    public String getDescription() {
        return this.description;
    }


    public String getStatusIcon() {
        return (this.isDone ? "v" : "x"); // "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public abstract String toString();
}

