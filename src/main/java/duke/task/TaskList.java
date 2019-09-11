package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeParserException;
import duke.exception.DukeListException;

import java.util.ArrayList;

/**
 * A TaskList is a list of Tasks that has been entered by the user.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList based on a list of Tasks.
     *
     * @param tasks the Tasks that will populate the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets a list of all the Tasks contained within the TaskList.
     *
     * @return the list of all the Tasks in the TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Gets the number of Tasks that is stored in the TaskList.
     *
     * @return the number of Tasks in the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Executes a Task on the TaskList based on the position of the Task in the TaskList.
     *
     * @param input the position of the Task on the TaskList.
     * @return the Task that has been executed.
     * @throws DukeException if the Task cannot be found on the TaskList, if the user did not enter
     *      a Task number or if the user did not specify which Task to be deleted.
     */
    public Task doTask(String input) throws DukeException {
        try {
            int itemIndex = Integer.parseInt(input);
            boolean isWithinSize = itemIndex > tasks.size() || itemIndex < 1;
            if (isWithinSize) {
                throw new DukeListException("The task number specified is not within the list.");
            } else {
                Task currTask = tasks.get(itemIndex - 1);
                currTask.doTask();
                return currTask;
            }
        } catch (NumberFormatException e) {
            throw new DukeParserException("The task specified is not a number.");
        }
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task the Task to be added into the TaskList.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task based on the position of the Task on the TaskList.
     *
     * @param input the position of the Task on the TaskList.
     * @return the Task that has been deleted from the TaskList.
     * @throws DukeException if the Task cannot be found on the TaskList, if the user did not enter
     *      a Task number or if the user did not specify which Task to be deleted.
     */
    public Task deleteTask(String input) throws DukeException {
        try {
            int deleteIndex = Integer.parseInt(input);
            boolean isWithinSize = deleteIndex > tasks.size() || deleteIndex < 1;
            if (isWithinSize) {
                throw new DukeListException("The task number specified is not within the list.");
            }
            Task deleted = tasks.remove(deleteIndex - 1);
            return deleted;
        } catch (NumberFormatException e) {
            throw new DukeParserException("The task specified is not a number.");
        }
    }

    /**
     * Finds Tasks based on a keyword that matches the tasks' description.
     *
     * @param input keyword
     * @return a TaskList containing the Tasks that match the keyword
     */
    public TaskList findTask(String input) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.getDescription().contains(input)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }
}
