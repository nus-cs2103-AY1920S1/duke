import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

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
     * Update name of a task.
     * @param index Index of task to be updated.
     * @param name New name for the task.
     * @return Updated task.
     */
    public Task updateName(int index, String name) {
        this.tasks.get(index).setName(name);
        return this.tasks.get(index);
    }

    /**
     * Update date of a task.
     * @param index Index of task to be updated.
     * @param date New date for the task.
     * @return Updated task.
     */
    public Task updateDate(int index, Date date) {
        this.tasks.get(index).setDate(date);
        return this.tasks.get(index);
    }

    /**
     * Update name and date of a task.
     * @param index Index of task to be updated.
     * @param name New name for the task.
     * @param date New date for the task.
     * @return Updated task.
     */
    public Task updateBoth(int index, String name, Date date) {
        this.tasks.get(index).setName(name);
        this.tasks.get(index).setDate(date);
        return this.tasks.get(index);
    }

    /**
     * List all tasks.
     * @return String representing all tasks.
     */
    public String listTasks() {
        if (this.tasks.isEmpty()) {
            return "No tasks present currently!";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int[] counter = new int[]{1};
        tasks.forEach(task -> {
            sb.append(String.format("     %d. %s\n", counter[0], task.toString()));
            counter[0] += 1;
        });
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Return a new TaskList filtered with the searched task.
     * @param findStr String to be searched for in the tasks.
     * @return TaskList where only the filtered tasks are included.
     */
    public TaskList findTasks(String findStr) {
        ArrayList<Task> newTasks = this.tasks.stream()
            .filter(task -> task.getName().contains(findStr))
            .collect(Collectors.toCollection(ArrayList::new));
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