import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructs TaskList used to store all Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Accessor for the ArrayList tasks.
     *
     * @return the ArrayList tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Accessor for the specified Task in the ArrayList tasks based in the index.
     *
     * @return the Task indexed at the ArrayList tasks.
     */
    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    /**
     * Removes the specified Task from the ArrayList tasks.
     *
     * @param taskToBeRemoved the specified Task to be removed.
     */
    public void removeTask(Task taskToBeRemoved) {
        tasks.remove(taskToBeRemoved);
    }

    /**
     * Gets the number of tasks in the ArrayList tasks.
     *
     * @return size of the ArrayList tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Adds the specified Task from the ArrayList tasks.
     *
     * @param t the specified Task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void replaceTask(int idxToReplace, Task newTaskToReplace) {
        tasks.set(idxToReplace, newTaskToReplace);
    }

}
