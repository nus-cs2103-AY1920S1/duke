package duke.task;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    private int counter;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.counter = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.counter = tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task remove(int id) {
        return this.tasks.remove(id);
    }

    public Task get(int id) {
        return this.tasks.get(id);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
