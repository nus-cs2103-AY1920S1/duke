package duke.tasks;

import java.io.Serializable;

import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public int getSize() {
        return taskList.size();
    }
}
