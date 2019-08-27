import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskStorage;

    public TaskList(ArrayList<Task> listOfTask) {
        this.taskStorage = listOfTask;
    }

    public Task get(int pos) {
        return taskStorage.get(pos);
    }

    public ArrayList<Task> get() {
        return taskStorage;
    }

    public int size() {
        return taskStorage.size();
    }

    public void add(Task task) {
        taskStorage.add(task);
    }

    /**
     * Removes task from the list.
     * @param index The index of task that the user wishes to delete.
     */
    public void removeTask(int index) {
        taskStorage.remove(index);
    }
}
