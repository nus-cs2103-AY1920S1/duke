/**
 * Represents a task object.
 */
public class Task {
    protected String description;
    protected String type;
    protected String date;
    protected String symbol;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     * @param description task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Another constructor for Task class.
     * @param description task description.
     * @param isDone indicates if item has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Obtains status icon to indicate in Duke if item is done.
     * @return unicode.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // returns check mark if done, cross symbol if undone
    }

    /**
     * Obtains boolean string to indicate in text file if item is done.
     * @return string.
     */
    public String getBoolean() {
        return (isDone ? "1" : "0");
    }

    /**
     * Gets task description.
     * @return string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets command type.
     * @return string.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets symbol of command type.
     * @return string.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets date in string.
     * @return string.
     */
    public String getDateString() {
        return date;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
}