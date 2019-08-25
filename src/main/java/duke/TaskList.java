package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public int getListSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
}
