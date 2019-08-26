package duke.task;

import java.util.Date;

public class Deadline extends Task {

    private Date deadlineTime;

    public Deadline(String deadlineName, Date deadlineTime) {
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

    @Override
    public String toDelimitedString() {
        return String.format("%c | %c | %s | %s", this.getRepLetter(), this.isDone() ? 'T' : 'F', super.getTaskName(), this.deadlineTime);
    }
}
