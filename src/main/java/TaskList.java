package main.java;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        tasks.add(null); // leave index 0 unused for clarity.
    }

    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    public Task delete(int idx) {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException(":'( OOPS!!! There's no such task index.");
        }
        return tasks.remove(idx);
    }

    public Task get(int idx) throws DukeIndexOutOfBoundsException{
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException(":'( OOPS!!! There's no such task index.");
        }
        return tasks.get(idx);
    }

    public boolean markAsDone(int idx) {
        if (idx <= 0 || idx >= tasks.size()) {
            throw new DukeIndexOutOfBoundsException(":'( OOPS!!! There's no such task index.");
        }
        return tasks.get(idx).markAsDone();
    }

    public long count() {
        return tasks.size() - 1; // account for 1-indexing.
    }

    // Takes a list of Strings and numbers them in order, in a top-down list format.
    public String format() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task != null) {
                sb.append("\t").append(i).append(". ").append(task).append("\n");
            }
        }
        return sb.toString();
    }
}
