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

    public String toString() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                result = tasks.get(i).toString();
            } else {
                result = tasks.get(i).toString() + "\n";
            }
        }
        return result;
    }

    public ArrayList<String> findAllMatches(String desc) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).hasDescription(desc)) {
                result.add("    " + (i + 1) + ". " + tasks.get(i).toString());
            }
        }
        return result;
    }
}
