import java.util.ArrayList;

/** Class to represent a list of tasks. */
class TaskList {
    // ArrayList to maintain list of tasks
    private ArrayList<Task> tasks;

    /**
     * Constructor for the object.
     * @param tasks List of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a task to task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * List all tasks.
     * @return String representing all tasks.
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("     %d. %s\n", i + 1, this.tasks.get(i).toString()));
        }
        return sb.toString();
    }

    /**
     * Return a new TaskList filtered with the searched task.
     * @param findStr String to be searched for in the tasks.
     * @return TaskList where only the filtered tasks are included.
     */
    public TaskList findTasks(String findStr) {
        ArrayList<Task> newTasks = new ArrayList<Task>();
        for (Task task : this.tasks) {
            if (task.getName().contains(findStr)) {
                newTasks.add(task);
            }
        }
        return new TaskList(newTasks);
    }

    /**
     * Mark a task as done in the list.
     * @param index Index of task to mark done.
     * @return Task marked as done.
     */
    public Task taskDone(int index) {
        this.tasks.get(index - 1).markDone();
        return this.tasks.get(index - 1);
    }

    /**
     * Remove a task from the list.
     * @param index Index of task to remove.
     * @return Removed task.
     */
    public Task removeTask(int index) {
        return this.tasks.remove(index - 1);
    }

    /**
     * Get the list of tasks.
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}