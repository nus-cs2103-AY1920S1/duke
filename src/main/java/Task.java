import java.util.Scanner;

public class Task {
    protected String type;
    protected String description;
    protected String date;
    protected boolean isDone;

    public Task(String type, String description, String date) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUnDone() {
        this.isDone = false;
    }

    public String toString() {
        return String.format("[%s][%s] %s%s", this.type,
                this.getStatusIcon(),
                this.description,
                this.date);
    }
}