package duke.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int chosenTaskNo) {
        return this.tasks.get(chosenTaskNo - 1);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task doDoneTask(int chosenTaskNo) {
        Task doneTask = this.tasks.get(chosenTaskNo - 1);
        doneTask.setDone(true);
        return doneTask;
    }

    public Task doDeleteTask(int chosenTaskNo) {
        Task deletedTask = this.tasks.get(chosenTaskNo - 1);
        this.tasks.remove(chosenTaskNo - 1);
        return deletedTask;
    }

}
