package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList deals with operations pertaining to the task list.
 */
public class TaskList {

    private List<Task> list;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Another constructor for TaskList class.
     *
     * @param tasks Tasks to load into the task list.
     */
    public TaskList(List<Task> tasks) {
        list = new ArrayList<>();
        for (Task task : tasks) {
            list.add(task);
        }
    }

    public List<Task> getList() {
        return list;
    }

    /**
     * Adds a task into the task list.
     *
     * @param task Task to be added into the list.
     */
    public void addToList(Task task) {
        list.add(task);
    }

    /**
     * Returns the size of the task list.
     *
     * @return int Size of task list.
     */
    public int getListSize() {
        return list.size();
    }

    /**
     * Returns the task after marking it as done.
     *
     * @param num Number of the tasks in the task list.
     * @return task Task that has been marked as done.
     */
    public Task markAsDone(int num) {
        Task task = list.get(num - 1);
        task.setDone();
        return task;
    }

    /**
     * Returns the deleted task.
     *
     * @param num Number of the task in the task list.
     * @return task Task that has been deleted.
     */
    public Task delete(int num) {
        Task deletedTask = list.get(num - 1);
        list.remove(num - 1);
        return deletedTask;
    }

    /**
     * Prints the task list in order of which task is added first.
     */
    public void printList() {
        int i = 1;
        for (Task task : list) {
            System.out.println("    " + i + ". " + task);
            i++;
        }
    }
}
