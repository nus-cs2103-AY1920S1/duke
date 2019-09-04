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

    void setCompleted() {
        this.isCompleted = true;
    }

    String getMark() {
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
}
