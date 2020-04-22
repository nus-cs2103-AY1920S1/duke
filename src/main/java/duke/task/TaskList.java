package duke.task;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskList extends ArrayList<Task> {
    private static final long serialVersionUID = 1L;

    @Override
    public TaskList clone() {
        Task[] tasksToClone = new Task[this.size()];
        this.toArray(tasksToClone);

        TaskList clonedTasks = new TaskList();

        Stream.of(tasksToClone).map(task -> task.clone()).forEach(task -> clonedTasks.add(task));

        return clonedTasks;
    }
}