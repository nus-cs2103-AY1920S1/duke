package duke.object;

import java.util.ArrayList;

/**
 * Represents a user's task list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }

    public Task getTask(int idx) {
        return this.tasks.get(idx);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void setDone(int idx) {
        this.tasks.get(idx).setDone();
    }

    public Task delete(int idx) {
        return this.tasks.remove(idx);
    }

    public String toString() {
        StringBuilder ls = new StringBuilder();
        for (int i = 1; i <= this.tasks.size(); i++) {
            ls.append(String.format("    %d. %s\n", i, this.tasks.get(i - 1)));
        }
        return ls.toString();
    }
}
