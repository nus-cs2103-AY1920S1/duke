package duke.util;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks defined by the user.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Initialises a new TaskList with no tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with preloaded tasks.
     * @param taskList ArrayList of Task objects to be loaded
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Deletes a Task.
     * @param deletedItemNo index of the task
     * @param ui ui object to print messages
     * @throws DukeException if task is not found
     */
    public void delete(int deletedItemNo, Ui ui) throws DukeException {
        try {
            Task itemRemoved = taskList.remove(deletedItemNo - 1);
            int numOfTask = taskList.size();
            ui.showDeleted(itemRemoved, numOfTask);
        } catch (Exception e) {
            throw new DukeException("    No more tasks to delete!");
        }
    }

    /**
     * Adds a Task.
     * @param task task object to be added
     * @param ui ui object to print messages
     */
    public void add(Task task, Ui ui) {
        taskList.add(task);
        int numOfTask = taskList.size();
        ui.showAdded(task, numOfTask);
    }

    /**
     * Marks the task as done.
     * @param itemNo index of the task to be marked
     * @param ui ui object to print messages
     */
    public void done(int itemNo, Ui ui) {
        Task task = taskList.get(itemNo - 1);
        task.markAsDone();
        ui.showDone(task);
    }

    /**
     * Initiates writing of data into file.
     * @param storage storage object to write data into
     * @throws DukeException if file not found
     */
    public void writeTo(Storage storage) throws DukeException {
        storage.writeToFile(taskList);
    }

    /**
     * Returns a string representation of the list of tasks.
     * @return a string representation of the list of tasks
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("     Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            output.append("     ").append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        return output.toString();
    }
}
