package DukeTask;

public class EventTask extends Task {
    public EventTask(String description, String timing) {
        super(description, timing);
        this.taskType = TaskType.event;
    }

    public String getStatusText() {
        return String.format("[E][%s] %s (at: %s)",
                getStatusIcon(),
                this.description,
                this.timing);
    }
}
