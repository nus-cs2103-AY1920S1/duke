package duke.main;

import duke.task.*;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public int getTasksSize() {
        return tasks.size();
    }
}
