package duke.task;

import duke.exception.DukeException;

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
     *      a Task number or if the user did not specify which Task to be done.
     */
    public Task doTask(String input) throws DukeException {
        if (input.split(" ").length > 1) {
            try {
                int itemIndex = Integer.parseInt(input.split(" ")[1]);
                if (itemIndex > tasks.size() || itemIndex < 1) {
                    throw new DukeException("The task number specified is not within the list.");
                } else {
                    Task currTask = tasks.get(itemIndex - 1);
                    currTask.doTask();
                    return currTask;
                }
            } catch (NumberFormatException e) {
                throw new DukeException("The task specified is not a number.");
            }
        } else {
            throw new DukeException("The task to be done is not specified.");
        }
    }

    /**
     * Adds a Todo Task to the TaskList.
     *
     * @param todo the Todo Task to be added into the TaskList.
     */
    public void addTodo(Todo todo) {
        tasks.add(todo);
    }

    /**
     * Adds a Deadline task to the TaskList.
     *
     * @param deadline the Deadline Task to be added into the TaskList.
     */
    public void addDeadline(Deadline deadline) {
        tasks.add(deadline);
    }

    /**
     * Adds an Event task to the TaskList.
     *
     * @param event the Event Task to be added into the TaskList.
     */
    public void addEvent(Event event) {
        tasks.add(event);
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
        if (input.split(" ").length > 1) {
            try {
                int deleteIndex = Integer.parseInt(input.split(" ")[1]);
                if (deleteIndex > tasks.size() || deleteIndex < 1) {
                    throw new DukeException("The task number specified is not within the list.");
                }
                Task deleted = tasks.remove(deleteIndex - 1);
                return deleted;
            } catch (NumberFormatException e) {
                throw new DukeException("The task specified is not a number.");
            }
        } else {
            throw new DukeException("The task to be deleted is not specified.");
        }
    }

    /**
     * Finds Tasks based on a keyword that matches the tasks' description.
     *
     * @param input keyword
     * @return a TaskList containing the Tasks that match the keyword
     * @throws DukeException if multiple keywords are given
     */
    public TaskList findTask(String input) throws DukeException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        String keyword = input.split(" ", 2)[1];
        for (Task task: this.tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }
}
