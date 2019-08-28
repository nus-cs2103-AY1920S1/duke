import java.io.Serializable;

public class Task implements Serializable {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    void markAsDone() {
        isDone = true;
    }

    String saveFormat() {
        return String.format("%s|%s", this.description, this.isDone);
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
