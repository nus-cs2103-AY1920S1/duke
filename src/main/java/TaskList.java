import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Appends new task to the back of the list.
     * 
     * @param task New task.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the index-th task as done.
     * 
     * @param index Task index.
     * @throws DukeException if invalid index is given.
     */
    public Task markAsDone(int index) throws DukeException {
        index--;
        validateIndex(index);
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    /**
     * Deletes the index-th task as done.
     * 
     * @param index Task index.
     * @throws DukeException if invalid index is given.
     */
    public Task deleteTask(int index) throws DukeException {
        index--;
        validateIndex(index);
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Returns the index-th task.
     * 
     * @param index Task index.
     * @throws DukeException if invalid index is given.
     */
    public Task get(int index) throws DukeException {
        index--;
        validateIndex(index);
        return tasks.get(index);
    }

    /**
     * Returns the size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list string presentation for hard disk.
     */
    public String printTasksForHardDisk() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            builder.append(tasks.get(i).toStringForHardDisk());
            builder.append("\n");
        }
        return builder.toString();
    }

    private void validateIndex(int index) throws DukeException {
        if (!(0 <= index && index < tasks.size())) {
            throw new DukeException("Invalid task index.");
        }
    }
}
