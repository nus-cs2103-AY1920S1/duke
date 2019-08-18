package duke;

import duke.task.Task;
import duke.task.TaskList;

public class TaskListStub extends TaskList {
    private boolean isEmpty = true;

    @Override
    public void add(Task t) {
        isEmpty = false;
    }

    @Override
    public Task delete(int i) {
        isEmpty = true;
        return new TaskStub();
    }

    @Override
    public int size() {
        return isEmpty ? 0 : 1;
    }
}
