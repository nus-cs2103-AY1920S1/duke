package duke.tasklist;

import duke.task.Task;

import java.util.LinkedList;

public class TaskList extends LinkedList<Task> {
    public static LinkedList<Task> taskList;
    public TaskList() {
        taskList = new LinkedList<>();
    }

    public TaskList(LinkedList<Task> tasks) {
        taskList = new LinkedList<>(tasks);
    }

    @Override
    public int size() {
        return taskList.size();
    }

    @Override
    public Task get(int n) {
        return taskList.get(n);
    }
}
