package duke.main;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of Task objects. A <code>TaskList</code> object corresponds to
 * a list of Task objects of form ToDo, Event and Deadline and specifies operations
 * that can be performed on the list.
 */
public class TaskList {
    private ArrayList<Task> tasksList;

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public void addItem(Task task) {
        tasksList.add(task);
    }

    public int size() {
        return tasksList.size();
    }

    /**
     * Updates a task number in the list with status of done.
     *
     * @param number the task number in the list to update
     */
    public Task markNumberedTaskAsDone(int number) throws DukeException {
        Task task = getTaskAtIndex(number - 1);
        task.markAsDone();
        return task;
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    public Task deleteTaskAtNumber(int position) {
        return tasksList.remove(position - 1);
    }

    public Task getTaskAtIndex(int position) throws DukeException {
        try {
            return tasksList.get(position);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(ex.getMessage());
        }
    }

}
