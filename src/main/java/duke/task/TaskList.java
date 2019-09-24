package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

import static duke.ui.Message.MESSAGE_REDO_UNSUPPORTED;
import static duke.ui.Message.MESSAGE_UNDO_UNSUPPORTED;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list containing the elements of the specified ArrayList of Tasks,
     * in the order they are returned by the list's iterator.
     *
     * @param tasks the ArrayList of Tasks whose tasks are to be placed into this task list
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = cloneTasks(tasks);
    }

    /**
     * Clones a list of tasks (deep copy).
     *
     * @param tasks tasks to be cloned
     * @return the deep copy of the tasks to be cloned
     */
    public static ArrayList<Task> cloneTasks(ArrayList<Task> tasks) {
        ArrayList<Task> copy = new ArrayList<>();
        for (Task task : tasks) {
            copy.add(task.copy());
        }
        return copy;
    }

    /**
     * Returns an ArrayList of tasks in the list.
     *
     * @return all the tasks in the list
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = cloneTasks(tasks);
    }

    /**
     * Returns the task at the specified position in this task list.
     *
     * @param index the 1-based index of the task to return
     * @return the task at the specified position in this task list
     * @throws DukeException if the index is out of range (index < 0 || index >= size())
     */
    public Task getTaskByIndex(int index) throws DukeException {
        try {
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    String.format("Task No.%d is not present in your list. "
                            + "Please enter a valid task ID.", index));
        }
    }

    /**
     * Appends the specified task to the end of this task list.
     *
     * @param task the task to be appended to the end of this task list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the task at the specified position in this task list as done and returns the task.
     *
     * @param index the 1-based index of the task to be marked as done
     * @return the task at the specified position in this task list after marking it as done
     * @throws DukeException if the index is out of range (index < 0 || index >= size())
     */
    public Task completeTask(int index) throws DukeException {
        Task task = getTaskByIndex(index);
        task.markAsDone();
        return task;
    }

    /**
     * Removes the task at the specified position in this task list.
     * Shifts any subsequent tasks to the left (subtracts one from their indices).
     *
     * @param index the 1-based index of the task to be removed
     * @return the task that was removed from the list
     * @throws DukeException if the index is out of range (index < 0 || index >= size())
     */
    public Task deleteTaskByIndex(int index) throws DukeException {
        Task task = getTaskByIndex(index);
        tasks.remove(task);
        return task;
    }

    /**
     * Clears all the tasks in this task list.
     *
     * @throws DukeException if the task list is empty
     */
    public void clearTasks()throws DukeException {
        if (isEmpty()) {
            throw new DukeException("No need to clear your task list "
                    + "since it is empty.");
        }
        tasks.clear();
    }

    /**
     * Returns the number of tasks in this task list.
     *
     * @return the number of tasks in this task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns true if this task list contains no tasks.
     *
     * @return true if this task list contains no tasks
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Filters this task list and returns a new task list
     * which only contains tasks with keyword appearing in its description.
     *
     * @param keyWord the keyword to be used for filtering this task list
     * @return a TaskList with all the tasks containing the keyWord
     */
    public TaskList filterByKeyWord(String keyWord) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyWord)) {
                newList.add(task);
            }
        }
        return new TaskList(newList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            sb.append(String.format("%d.%s", i, task));
            if (i != tasks.size()) {
                sb.append(Ui.LS);
            }
        }
        return sb.toString();
    }

    public boolean canUndo() {
        return false;
    }

    public boolean canRedo() {
        return false;
    }

    public void undo() throws DukeException {
        throw new DukeException(MESSAGE_UNDO_UNSUPPORTED);
    }

    public void redo() throws DukeException {
        throw new DukeException(MESSAGE_REDO_UNSUPPORTED);
    }

    public void commit() {
    }
}