package main;

import task.*;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }

    public int length() {
        return tasks.size();
    }

    public Task removeTask(int i) {
        return tasks.remove(i);
    }

    public Task addTask(Task t) {
        tasks.add(t);
        return t;
    }
}
