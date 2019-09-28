package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks to be handled by Duke. It has methods
 * for handling each task object in the list, such as adding, deleting,
 * marking as done etc.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public void doneTask(Task task) {
        task.setIsDone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }
}
