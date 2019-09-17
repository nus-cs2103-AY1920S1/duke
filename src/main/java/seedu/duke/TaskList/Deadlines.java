package seedu.duke.TaskList;

import seedu.duke.TaskList.Task;

public class Deadlines extends Task {

    public Deadlines(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "D | " + this.getStatusNumber() + " | " + this.description + " | " + this.time;
    }

    @Override
    public String toActionString() {
        return "[" + this.taskType + "][" + this.getStatusIcon() + "] " + this.description + " (by: " + this.time + ")";
    }
}
