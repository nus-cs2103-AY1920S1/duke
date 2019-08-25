package DukeTask;

public class DeadlineTask extends Task {
    public DeadlineTask(String description, String deadline) {
        super(description, deadline);
        this.taskType = TaskType.deadline;
    }

    public String getStatusText() {
        return String.format("[D][%s] %s (at: %s)",
                getStatusIcon(),
                this.description,
                this.timing);
    }
}
