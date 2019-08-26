package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list that stores every <code>Task</code> object.
 * Corresponds to an <code>ArrayList</code> that contains the tasks.
 */
public class TaskList {

	protected ArrayList<Task> tasks;

	/**
	 * Constructor for <code>TaskList</code> that takes in an <code>ArrayList</code> that already contains a few tasks.
	 * @param tasks <code>ArrayList/code> that already contains a few tasks.
	 */
	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * Constructor for <code>TaskList</code> that does not take in any parameter.
	 * A new <code>ArrayList</code> is created to store tasks.
	 */
	public TaskList() {
		this.tasks = new ArrayList<Task>();
	}

	/**
	 * Returns the list containing the tasks.
	 * @return The list containing the tasks.
	 */
	public ArrayList<Task> getList() {
		return tasks;
	}

	/**
	 * Adds a <code>Task</code> to the list.
	 * @param task Task that is to be added.
	 */
	public void addTask(Task task) {
		tasks.add(task);
	}

	/**
	 * Deletes a <code>Task</code> from the list that corresponds to the provided index.
	 * @param index Index of task in the list that is to be deleted.
	 */
	public void deleteTask(int index) {
		tasks.remove(index);
	}

	/**
	 * Returns the <code>Task</code> in the list that corresponds to the provided index.
	 * @param index Index of task in the list.
	 * @return Task that corresponds to the provided index.
	 */
	public Task getTask(int index) {
		return tasks.get(index);
	}

	/**
	 * Returns the size of the list.
	 * @return Size of the list.
	 */
	public int getSize() {
		return tasks.size();
	}

}
