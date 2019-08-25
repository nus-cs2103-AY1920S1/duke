import java.text.SimpleDateFormat;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected SimpleDateFormat dateFormat;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String toFileString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}