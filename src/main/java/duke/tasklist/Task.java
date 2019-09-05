package duke.tasklist;

public class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getMark() {
        if (isCompleted) {
            return "✓";
        } else {
            return "✗";
        }
    }

    @Override
    public String toString() {
        return "[" + this.getMark() + "] " + this.getDescription() + "\n";
    }

    public String saveString() {
        return "";
    }
}
