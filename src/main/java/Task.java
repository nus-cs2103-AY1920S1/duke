public class Task {
    protected String description;
    protected Boolean isdone;

    public Task (String description) {
        this.description = description;
        this.isdone = false;
    }

    public String getStatusIcon() {
        return (isdone ? "\u2713" : "\u2718");
    }

    public void markasdone() {
        isdone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

}
