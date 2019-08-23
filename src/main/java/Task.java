import java.util.Arrays;
import java.util.Scanner;

public abstract class Task {
    private static final String DELIMITER = " ` ";

    private String description;
    private boolean isDone;

    static Task parse(String str) {
        String[] data = str.split(DELIMITER);
        String type = data[0];
        boolean isDone = Integer.parseInt(data[1]) == 1;
        String desc = data[2];

        Task task = null;
        switch (type) {
        case Todo.INITIAL:
            task = new Todo(desc);
            break;
        case Deadline.INITIAL:
            task = new Deadline(desc, data[3]);
            break;
        case Event.INITIAL:
            task = new Event(desc, data[3]);
            break;
        default:
            throw new IllegalArgumentException("Initial does not match any known initials");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    abstract String getInitial();

    String getDate() {
        return null;
    }

    String getPrefix() {
        return null;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    void markAsDone() {
        isDone = true;
    }

    String getStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        sb.append(getInitial());
        sb.append(DELIMITER);
        sb.append(isDone ? 1 : 0);
        sb.append(DELIMITER);
        sb.append(description);
        if (getDate() != null) {
            sb.append(DELIMITER);
            sb.append(getDate());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "[" + getInitial() + "]" + "[" + getStatusIcon() + "] " + description + " " +
                (getDate() == null ? "" : brackets(getPrefix() + getDate()));
    }

    private String brackets(String s) {
        return "(" + s + ")";
    }
}