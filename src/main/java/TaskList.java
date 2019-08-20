package main.java;

import java.util.Arrays;

public class TaskList {
    private static int n = 0; // counter for number of tasks in list.
    public Task[] tasks;

    public TaskList() {
        this.tasks = new Task[101]; // leave index 0 empty for clarity.
    }

    public Task add(Task task) {
        tasks[++n] = task;
        return task;
    }

    public Task get(int idx) {
        return tasks[idx];
    }

    public boolean markAsDone(int idx) {
        return tasks[idx].markAsDone();
    }

    public long count() {
        return Arrays.stream(tasks)
                    .filter(x -> x != null)
                    .count();
    }

    // Takes a list of Strings and numbers them in order, in a top-down list format.
    public String format() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.length; i++) {
            Task task = tasks[i];
            if (task != null) {
                sb.append("\t").append(i).append(". ").append(task).append("\n");
            }
        }
        return sb.toString();
    }
}
