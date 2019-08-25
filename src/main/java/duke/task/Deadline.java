package duke.task;

public class Deadline extends Task {

    private String deadlineTime;

    public Deadline(String deadlineName, String deadlineTime) {
        super(deadlineName);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String getTaskName() {
        return super.getTaskName() + " (by: " + deadlineTime + ")";
    }

    @Override
    public char getRepLetter() {
        return 'D';
    }

}
