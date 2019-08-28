package duke.tasklist;

import duke.command.DukeIncorrectParameterTypeException;
import duke.DukeException;
import java.util.ArrayList;

/**
 * Class to represent a list of tasks
 */
public class TaskList {
	private ArrayList<Task> taskList;

	/**
	 * Constructs an empty task list
	 */
	public TaskList() {
		taskList = new ArrayList<Task>();
	}

	/**
	 * Adds a task to the task list
	 * @param t The task to be added to the list
	 * @return The task which was added to the list
	 */
	public Task addTask(Task t) {
		taskList.add(t);
		return t;
	}

	/**
	 * Returns a copy of the array list used to store the tasks in the list
	 * @return An array list which contains the tasks in the task list
	 */
	public ArrayList<Task> list() {
		return new ArrayList<Task>(taskList);
	}

	/**
	 * Returns the number of tasks in the task list
	 * @return The number of tasks in the task list
	 */
	public int size() {
		return taskList.size();
	}

	/**
	 * Deletes the task with the number corresponding to the number provided, if it exists
	 * @param id The number corresponding to the task in the task list
	 * @return The task which was deleted from the list, if it exists
	 * @throws DukeException The exception thrown when an error occurs when trying to delete the task from the list
	 */
	public Task delete(int id) throws DukeException {
		try {
			return taskList.remove(id - 1);
		} catch (IndexOutOfBoundsException ex) {
			// task id does not correspond to task in list
			throw new DukeNoCorrespondingTaskException(id);
		}
	}

	/**
	 * Adds the tasks to the task list
	 * @param task The task to be added to the list
	 * @return The task which was added to the list
	 */
	public Task add(Task task) {
		taskList.add(task);
		return task;
	}

	/**
	 * Deletes the task with the number corresponding to the number provided, if it exists
	 * @param id The number corresponding to the task in the task list, in string form
	 * @return The task which was deleted from the list, if it exists
	 * @throws DukeException The exception thrown when an error occurs when trying to delete the task from the list
	 */
	public Task delete(String id) throws DukeException {
		try {
			return delete(Integer.parseInt(id));
		} catch (NumberFormatException ex) {
			throw new DukeIncorrectParameterTypeException("Integer", id);
		}
	}

	/**
	 * Marks the task with the number corresponding to the number provided, if it exists, as done
	 * @param id The number corresponding to the task in the task list
	 * @return The Task which was marked as done
	 * @throws DukeException The exception thrown when an error occurs when trying to mark the task as done
	 */
	public Task complete(int id) throws DukeException {
		try {
			return taskList.get(id - 1).complete();
		} catch (IndexOutOfBoundsException ex) {
			throw new DukeNoCorrespondingTaskException(id);
		}
	}

	/**
	 * Marks the task with the number corresponding to the number provided, if it exists, as done
	 * @param id The number corresponding to the task in the task list, in string form
	 * @return The Task which was marked as done
	 * @throws DukeException The exception thrown when an error occurs when trying to mark the task as done
	 */
	public Task complete(String id) throws DukeException {
		try {
			return complete(Integer.parseInt(id));
		} catch (NumberFormatException ex) {
			throw new DukeIncorrectParameterTypeException("Integer", id);
		}
	}
}