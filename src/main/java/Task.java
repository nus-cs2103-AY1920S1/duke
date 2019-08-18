/**
 *
 */
public class Task {
    // Instance variables
    protected String description;
    protected boolean isDone;

    // Constructors
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    // Getters & Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void markAsDone() {
        isDone = true;
    }

    /**
     * dot. Provided in the CS2103T task description.
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String printStatus() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}