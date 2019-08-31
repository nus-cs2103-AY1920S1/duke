package Tasks;

import Exceptions.DukeException;
import Tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the total number of lists.
     * @return size of list as an integer
     */
    public int getListSize() {
        return tasks.size();
    }

    public String getTaskString(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Adds a new task to the list of tasks
     * @param task task to be added
     * @throws DukeException
     */
    public void addNewTask(Task task) throws DukeException {
        tasks.add(task);
    }

    /**
     * Removes a task from the list of tasks and returns it.
     * @param index index of file to be deleted
     * @return deleted task
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks the indicated task as done.
     * @param taskIndex task to be marked as done
     */
    public void markAsDone(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
    }

    /**
     * Returns the list of tasks as an array list.
     * @return
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
