import duke.task.Task;

import java.util.Date;

public class TodoTaskStub extends Task {

    public TodoTaskStub(String description) {
        super(description);
    }

    public TodoTaskStub(String description, boolean isDone) {
        super(description);
    }

    @Override
    public String toString() {
        return "Todo Task";
    }
}
