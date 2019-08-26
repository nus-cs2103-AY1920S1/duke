public class Task {
    private String description;
    private boolean isDone;

    public Task(String des) {
        description = des;
        isDone = false;
    }

    public String getStatusIcon(){
        return isDone ? "+" : "-";
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
