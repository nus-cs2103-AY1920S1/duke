package duke.task;

import java.util.List;

public class TaskListStub extends TaskList {
    public Task lastAction;

    @Override
    public void add(Task task) {
        lastAction = task;
    }

    @Override
    public Task remove(int index) {
        return lastAction = new TaskImpl("remove " + index);
    }

    @Override
    public Task get(int index) {
        return lastAction = new TaskImpl("get " + index);
    }

    @Override
    public int size() {
        return 5;
    }

    @Override
    public List<String> getAsLines() {
        return List.of(lastAction.toString());
    }
}
