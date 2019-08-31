package duke.task;

import java.util.ArrayList;

/**
 * Object interface for duke to manage task list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
