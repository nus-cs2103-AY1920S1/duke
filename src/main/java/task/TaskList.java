package task;

import java.util.ArrayList;

/**
 * Encapsulates tasks into a TaskList.
 * Responsible for recording all tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Delete a task in TaskList.
     * @param index index number of task to be deleted.
     * @return the deleted task.
     */
    public Task deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);

        assert !task.equals(tasks.get(index)) : "Might have deleted the wrong task";

        return task;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Constructor to be called when there's no file read.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

}