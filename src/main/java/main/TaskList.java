package main;

import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getTaskList() {
        return this.list;
    }

    public void updateTaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTaskList(Task task) {
        list.add(task);
    }

}
