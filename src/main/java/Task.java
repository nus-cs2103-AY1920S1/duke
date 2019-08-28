/**
 * Represents a general task
 */
public class Task {
    String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    void setDone() {
        this.isDone = true;
    }

    String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    int getStatus() {
        return (this.isDone ? 1 : 0);
    }

    String store(){
        return getStatus() + "|" + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}