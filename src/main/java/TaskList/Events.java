package TaskList;

import TaskList.Task;

public class Events extends Task {

    public Events(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "E | " + this.getStatusNumber() + " | " + this.description + " | " + this.time;
    }

    @Override
    public String toActionString() {
        return "[" + this.taskType + "][" + this.getStatusIcon() + "] " + this.description + " (at: " + this.time + ")";
    }
}
