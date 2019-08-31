import java.util.ArrayList;

import java.io.Serializable;

/**
 * Represents a ArrayList which stores task and implemenets Serializable, allowing it to be
 * serialized by the Storage class.
 * 
 * @see Storage#Storage(String)
 */
class TaskList implements Serializable {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList, which instantiates a new ArrayList which stores Tasks.
     * 
     * @see ArrayList#ArrayList()
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds task to the ArrayList.
     * 
     * @param task Task to be added to the ArrayList
     * @see Task#Task(String)
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns a task that is deleted from the Tasklist/ArrayList.
     * 
     * @param index The index of the task to be deleted
     * @return The task that is deleted
     * @throws DukeException When failing to delete a task due to out-of-bound error.
     */
    public Task deleteTask(int index) throws DukeException {
        try {
            return this.taskList.remove(index);
        } catch (Exception e) {
            throw new DukeException("Oof. The given task number is not found.");
        }
    }

    /**
     * Returns the number of elements of the ArrayList.
     * 
     * @return The size of the list
     */
    public int listSize() {
        return this.taskList.size();
    }

    /**
     * Returns the task of the given index.
     * 
     * @param index The index of the task to be retrieve
     * @return The task from the task list of the given index
     * @throws DukeException When the Task index is not found
     */
    public Task getTask(int index) throws DukeException {
        try {
            return this.taskList.get(index);
        } catch (Exception e) {
            throw new DukeException("Oof. The given task number is not found.");
        }
    }

}