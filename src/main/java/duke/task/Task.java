package duke.task;

public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getType() {
        return "U";
    }

    public String getStatusIcon() {
        // Return tick or X symbols
        return (isDone ? "\u2713" : "\u2718"); // Either a tick or a cross symbol
    }

    /**
     * This function puts the content of the Task into a format suitable for storing into a file.
     * @return String
     */
    public String getFileLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getType());
        sb.append(" | ");

        if (this.isDone) {
            sb.append("1");
        } else {
            sb.append("0");
        }

        sb.append(" | ");
        sb.append(this.description);
        return sb.toString();
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public int compareTo(Task other) {
        return this.description.compareTo(other.description);
    }
}
