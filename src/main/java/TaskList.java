import exception.DukeException;
import tasks.Task;

import java.util.ArrayList;

/**
 * Contains the task list and updates tasks in the list.
 */
public class TaskList {
    ArrayList<Task> list = new ArrayList<Task>();

    public TaskList() {}

    public TaskList(ArrayList<Task> list) throws DukeException {
        this.list = list;
    }

    /**
     * Gets the list of tasks.
     * @return The list of tasks
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Gets the task of index n.
     * @param n The index of the task in the list
     * @return The task of index n
     */
    public Task getTask(int n) {
        return list.get(n);
    }

    /**
     * Gets the size of the task list.
     * @return The size of the task list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Adds new task to the task list.
     * @param t The task to be added to the task list
     */
    public void addTask(Task t) {
        list.add(t);
    }

    /**
     * Marks task of index n in the list as done.
     * @param n The index of the task to be marked as done in the list
     * @return The task after it is marked as done
     */
    public Task markTaskAsDone(int n) {
        Task task = list.get(n);
        task.markAsDone();
        return task;
    }

    /**
     * Removes task of index n from the list.
     * @param n The index of the task to be removed from the list
     * @return The task after it is removed from the list
     */
    public Task removeTask(int n) {
        return list.remove(n);
    }

    /**
     * Prints the list of all tasks in the task list.
     */
    public void printList() {
        int length = list.size();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= length; i++) {
            Task task = list.get(i - 1);
            System.out.println(i + ". " + task);
        }
    }
}
