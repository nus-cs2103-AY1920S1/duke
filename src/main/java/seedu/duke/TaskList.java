package seedu.duke;

import java.util.ArrayList;

/**
 * TaskList is a class that represents the list of tasks and contains operations to manipulate the list.
 */
public class TaskList {

    private static ArrayList<Task> taskList;

    /**
     * Constructor of the Tasklist class
     */
    public TaskList() {
        this.taskList = new ArrayList<>();;
    }

    /**
     * Another constructor of the Tasklist class with the list of tasks as parameter
     *
     * @param taskList the arraylist of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the task to the list of tasks.
     *
     * @param t the task to be added
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes the task from the list of tasks.
     *
     * @param index the index of the task to be deleted
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the task from the list of tasks.
     *
     * @param index the index of the task to be returned
     * @return the task
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return the size of the list of tasks
     */
    public int getSize() {
        return taskList.size();
    }

}
