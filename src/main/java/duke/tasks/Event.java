package duke.tasks;

public class Event extends Task {

    private String time;



    public Event(String description, String time) {
        super(description);
        this.time = time;
        this.type = TaskType.EVENT;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s(at: %s)", getStatusIcon(),
                getDescription(), getTime());
    }
}