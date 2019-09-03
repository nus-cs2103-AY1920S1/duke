package duke.data;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> _tasks;

    public TaskList() {
        this._tasks = new ArrayList<>();
    }

    public TaskList(TaskList taskList) {
        this._tasks = taskList.getList();
    }

    public void addTask(Task task) {
        this._tasks.add(task);
    }

    public void removeTask(int index) {
        this._tasks.remove(index);
    }

    public void markTaskAsDone(int index) {
        this._tasks.get(index).markAsDone();
    }

    public Task getTask(int index) {
        return this._tasks.get(index);
    }

    public int getSize() {
        return this._tasks.size();
    }

    public ArrayList<Task> getList() {
        return this._tasks;
    }
}
