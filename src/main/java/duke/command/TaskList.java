package duke.command;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Stores the list of tasks and supports the addition, deletion, and mark as done of tasks.
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * An empty constructor for TaskList.
     */
    public TaskList() {
        this.list =  new ArrayList<>();
    }

    /**
     * Constructs a new TaskList with the given ArrayList of Tasks.
     * @param list an ArrayList of Tasks which content is going to be saved in the new TaskList object
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a new task.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Returns the list of tasks in this TaskList.
     * @return an ArrayList of Tasks containng the tasks
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Removes a task with of the position i - 1.
     * @param i position of the task (starts from 1)
     * @return the Task which is removed from this TaskList
     */
    public Task removeTask(int i) {
        return list.remove(i - 1);
    }

    /**
     * Returns the current number of tasks stored in this TaskList.
     * @return an integer representing the number of tasks in this tasklist
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Marks the task stored at position i + 1 as done.
     * @return the modified Task
     */
    public Task markTaskAsDone(int i) {
        Task task = list.get(i - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Clear the ArrayList
     */
    public void clean() {
        list = new ArrayList<>();
    }
}
