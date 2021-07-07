package myduke;

import myduke.task.Task;

public class TaskStub extends Task {
    TaskStub() {
        super("");
    }

    @Override
    public String getDataBaseFormat() {
        return null;
    }

    @Override
    public String toString() {
        return "Test Stub";
    }
}
