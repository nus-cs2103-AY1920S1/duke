public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void markAsDone() {
        setDone(true);
    }

    public String getAscii() {
        String doneOrNot = isDone() ? "1" : "0";
        return doneOrNot + " | " + getDescription();
    }

    @Override
    public String toString() {
        String value = "[" + getStatusIcon() + "]" + " " + getDescription();
        return value;
    }
}