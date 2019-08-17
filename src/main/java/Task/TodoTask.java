package Task;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    public String getStatusText() {
        return String.format("[T][%s] %s", getStatusIcon(), this.description);
    }
}
