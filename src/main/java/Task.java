public class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method is used return status icon of a task.
     *
     * @return String status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * This method is used return description of a task.
     *
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method is used to return extra information such as
     * the time of a task (if any).
     *
     * @return String extra information
     */
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

    /**
     * This method is used to mark a task as done.
     *
     * @return Nothing
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

