package duke.tasks;

public class Deadline extends Task {

    private String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
        this.type = TaskType.DEADLINE;
    }



    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s(by: %s)", getStatusIcon(),
                getDescription(), getTime());
    }
}