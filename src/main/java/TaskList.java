import java.util.ArrayList;

/**
 * Creates a task list for users to add new task.
 */
public class TaskList {

	ArrayList<Task> tasks;
	int counter;

	public TaskList() {
		counter = 0;
		tasks = new ArrayList<>();
	}

	public TaskList(ArrayList<Task> loadFile) {
		tasks = new ArrayList<>();
		tasks.addAll(loadFile);
		counter = tasks.size();
	}

	/**
	 * Adds the current task to the task list.
	 *
	 * @param task Current task.
	 */
	public void addTask(Task task) {
		tasks.add(task);
	}


	/**
	 * Deletes the specified task in the task list.
	 *
	 * @param index Index of the tasks to be deleted.
	 */
	public void deleteTask(int index) {
		tasks.remove(index);
	}


	/**
	 * Returns the number of tasks currently in the task list.
	 *
	 * @return
	 */
	public int getCounter() {
		return tasks.size();
	}

	/**
	 * Returns the tasks at the specified index in the task list.
	 *
	 * @param index Index of the task to be retrieved.
	 * @return The task
	 */
	public Task getTask(int index) {

		return tasks.get(index);
	}

	/**
	 * Returns the arraylist of tasks.
	 *
	 * @return List of task.
	 */
	public ArrayList<Task> getTaskList() {

		return tasks;
	}
}
