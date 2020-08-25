package seedu.duke.util;

import seedu.duke.exceptions.DukeException;
import seedu.duke.tasks.Task;

import java.util.ArrayList;

/**
 * Class that stores the list of tasks that the application uses.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor that initialises an ArrayList to store the tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Overloaded constructor that assigns an existing ArrayList to the local variable.
     * @param taskList ArrayList to assign the local variable to.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds task to local variable.
     *
     * @param task Task to add.
     */
    public void addToList(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes task from local variable.
     *
     * @param entry Entry number of task to delete.
     * @throws DukeException Throws if entry of task does not contain a task.
     */
    public void deleteFromList(int entry) throws DukeException {
        try {
            taskList.remove(entry - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("OOPS!!! I'm sorry, but this task does not exist.");
        }
    }

    /**
     * Marks task at entry as done.
     *
     * @param entry Entry number of task to mark done.
     * @throws DukeException Throws if entry of task does not contain a task.
     */
    public void markAsDone(int entry) throws DukeException {
        try {
            taskList.get(entry - 1).setDone();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("OOPS!!! I'm sorry, but this task does not exist.");
        }
    }

    /**
     * Gets the task from the local variable.
     *
     * @param entry Entry number of task to get.
     * @return Task that is at the entry specified.
     */
    public Task getTask(int entry) {
        return taskList.get(entry - 1);
    }

    /**
     * Gets the size of the local variable.
     *
     * @return Size of the ArrayList of tasks.
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Gets the whole local variable.
     *
     * @return The whole ArrayList of tasks as is.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < taskList.size(); i++) {
            temp = temp + (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return temp;
    }
}
