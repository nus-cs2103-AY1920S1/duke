package Task;

public class EventTask extends Task {
    public EventTask(String description, String timing) {
        super(description, timing);
    }

    public String getStatusText() {
        return String.format("[E][%s] %s (at: %s)",
                getStatusIcon(),
                this.description,
                this.timing);
    }
}
