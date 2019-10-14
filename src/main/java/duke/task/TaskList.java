package duke.task;

import duke.ui.Ui;

import duke.exception.DukeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    List<Task> tasks;

    /**
     * Constructor to create a TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor to create a TaskList object with an existing Task list.
     * @param tasks Existing tasks list in the database.
     * @throws IOException If an I/O error is incurred.
     */
    public TaskList(List<Task> tasks) throws IOException {
        this.tasks = tasks;
    }

    /**
     * Gets the current tasks list.
     *
     * @return The list containing the Task objects.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a Task into the list.
     *
     * @param task Task object.
     * @param ui The Ui object we are currently using.
     * @throws DukeException If the input format is incorrect.
     */
    public String addTask(Task task, Ui ui) throws DukeException {
        int duplicate = checkDuplicate(task);
        if (duplicate != 0) {
            throw new DukeException("OOPS!!! Similar task in the list!\n"
                + "Task " + duplicate + ": " + tasks.get(duplicate - 1));
        }
        tasks.add(task);
        return ui.addedTask(task, tasks.size());
    }

    private int checkDuplicate(Task task) {
        String taskString = task.getDuplicateCheckString();
        int count = 0;
        for (Task t: tasks) {
            count++;
            if (taskString.equalsIgnoreCase(t.getDuplicateCheckString())) {
                return count;
            }
        }
        return 0;
    }

    /**
     * Deletes a Task in the list.
     *
     * @param taskNumber Task number to be removed from the list.
     * @param task Task object to be removed;
     * @param ui The Ui object we are currently using.
     * @throws DukeException If the input format is incorrect.
     */
    public String deleteTask(int taskNumber, Task task, Ui ui) {
        tasks.remove(taskNumber - 1);
        return ui.deletedTask(task, tasks.size());
    }

    /**
     * Marks a Task in the list as done.
     *
     * @param taskNumber Task number to be marked as done.
     * @param ui The Ui object we are currently using.
     * @throws DukeException If the input format is incorrect or if the task is already done.
     */
    public String doneTask(int taskNumber, Ui ui) {
        tasks.get(taskNumber - 1).markAsDone();
        return ui.doneTask(tasks, taskNumber);
    }
}
