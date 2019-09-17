package dude.task;

/**
 * Represents a task with corresponding description and completion status (isDone).
 */
public class Task {
    protected String type;
    protected String description;
    protected int isDone;

    public Task(String description, int isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone() {
        this.isDone = 1;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String doneSymbol = isDone == 1 ? "[+]" : "[  ]"; // "[✓]" : "[✘]"; "[\u2713]" : "[\u2718]";
        return doneSymbol + " " + description;
    }
}
