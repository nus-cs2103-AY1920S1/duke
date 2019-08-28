//package mypackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents editor to the list of tasks in the manager.
 */
public class TaskList {

    /**
     * List of tasks.
     */
    private List<Task> list;

    /**
     * Number of tasks.
     */
    private int noOfTasks;

    /**
     * Constructs the editor.
     * @param list list of tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.noOfTasks = list.size();
    }

    /**
     * Constructs a new editor.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
        this.noOfTasks = 0;
    }

    /**
     * Gets number of tasks.
     * @return number of tasks
     */
    public int getNoOfTasks() {
        return noOfTasks;
    }

    /**
     * Gets list of tasks.
     * @return list of tasks
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * Adds task to the list.
     * @param task task to be added
     */
    public void add(Task task) {
        list.add(task);
        noOfTasks++;
    }

    /**
     * Deletes task from the list.
     * @param no position of the deleted task
     */
    public void delete( int no) {
        list.remove(no -1);
        noOfTasks--;
    }

    /**
     * Completes a task in the list.
     * @param no position of the task to be completed
     */
    public void done(int no) {
        list.get(no - 1).markAsDone();
    }
}
