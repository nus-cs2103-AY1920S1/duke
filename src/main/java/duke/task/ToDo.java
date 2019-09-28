package duke.task;

public class ToDo extends Task {

    private char taskType = 'T';

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String status, String description) {
        super(description);
        this.setStatus(status);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] " + super.toString(), super.getStatusIcon());
    }

    @Override
    public char getTaskType() {
        return taskType;
    }
}
