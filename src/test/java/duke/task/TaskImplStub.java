package duke.task;

public class TaskImplStub extends Task {

    public TaskImplStub(String description) {
        super(description);
    }

    @Override
    public String encode() {
        return null;
    }

    @Override
    public Task copy() {
        return new TaskImplStub(getDescription());
    }
}
