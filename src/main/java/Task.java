/**
 * Represents a task object.
 */
public class Task {
    protected String description;
    protected String type;
    protected String date;
    protected String symbol;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // returns check mark if done, cross symbol if undone
    }

    public String getBoolean() {
        return (isDone ? "1" : "0");
    }

    public String getDescription() {
        return (description);
    }

    public String getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDateString() {
        return date;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}