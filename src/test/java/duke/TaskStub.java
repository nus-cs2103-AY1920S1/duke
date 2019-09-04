package duke;

import duke.task.Task;

public class TaskStub extends Task {
    TaskStub() {
        super("", false);
    }

    @Override
    public String getType() {
        return null;
    }
}
