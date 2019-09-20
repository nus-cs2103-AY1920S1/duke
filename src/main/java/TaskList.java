import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Removes the desired task based on its index in the task-list.
     * @param i the index of the task to be removed
     */
    public void removeTask(int i) {
        tasks.remove(i);
    }

    /**
     * Adds the desired task to the end of the task-list.
     * @param task the task to be added to the task-list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves the desired task in the task-list.
     * @param i the index of the task to be retrieved
     * @return task requested based on index in the ArrayList used to store the tasks
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Returns the number of tasks in the task-list.
     * @return number of tasks in the task-list
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Replaces the task in the tasklist with the new task.
     * @param index index of task to be replaced
     * @param newTask new task to replace
     */
    public void setTask(int index, Task newTask) {
        tasks.set(index, newTask);
    }
}
