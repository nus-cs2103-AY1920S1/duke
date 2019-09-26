package duke.task;

import java.util.ArrayList;

/**
 * Represents a tasklist object which handles list update.
 */
public class TaskList {

    ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public Task removeTask(int index) {
        Task currentTask = list.get(index - 1);
        list.remove(index - 1);
        return currentTask;
    }

    public Task doneTask(int index) {
        Task currentTask = list.get(index - 1);
        currentTask.markAsDone();
        return currentTask;
    }

    public int numberOfTasks() {
        return list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

}
