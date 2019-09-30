package duke.task;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        return 11;
    }

    @Override
    public Stream<Task> stream() {
        return IntStream.range(0, size()).mapToObj(i -> new TaskImpl("stream " + i));
    }

    @Override
    public List<String> getAsLines() {
        return List.of(lastAction.toString());
    }
}
