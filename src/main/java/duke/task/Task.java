package duke.task;

public class Task {
    boolean isDone;
    private String description;

    Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    Task(String description, boolean isDone) {
        this.isDone = isDone;
        this.description = description;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean hasKeywordsInDescription(String keyword) {
        if (description.contains(keyword)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toDataFormat() {
        int isDoneInt;
        if (isDone) {
            isDoneInt = 1;
        } else {
            isDoneInt = 0;
        }
        return " | " + isDoneInt + " | " + description;
    }
}