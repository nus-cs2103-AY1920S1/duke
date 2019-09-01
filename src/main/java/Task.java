public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getType() {
        return "Type";
    }

    public String getDescription() {
        return description;
    }

    public boolean find(String word) {
        if (description.contains(word)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}