public class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getExtraInfo() {
        if (symbol.equals("T")) {
            return "";
        } else if (symbol.equals("E")) {
            Event t = (Event) this;
            return t.at;
        } else {
            Deadline t = (Deadline) this;
            return t.by;
        }
    }

    public void markAsDone() {
        isDone = true;
    }

    //...
}

