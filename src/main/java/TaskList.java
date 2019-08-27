import java.util.ArrayList;

/**
 * Represents the collection of tasks in memory
 */
public class TaskList {
	private ArrayList<Task> taskArrayList;

	/**
	 * default constructor for taskList object, initializes a empty arraylist of tasks.
	 */
	public TaskList() {
		taskArrayList = new ArrayList<>();
	}

	/**
	 * overloaded constructor for taskList object. Assigns a ArrayList as the taskArraylist
	 * @param taskArrayList a arraylist of tasks to be used as the collection of tasks for duke
	 */
	public TaskList(ArrayList<Task> taskArrayList) {
		this.taskArrayList = taskArrayList;
	}

	/**
	 * adds a task into the taskList
	 * @param task the task to be added
	 * @return returns the same task object that was added
	 */
	public Task addTask(Task task) {
		taskArrayList.add(task);
		return task;
	}

	/**
	 * removes a task from the taskList
	 * @param index the index of the task in the taskList. Note that the index starts from 1 for user ease.
	 * @return the task that was removed from the taskList
	 */
	public Task removeTask(int index) {
		return this.taskArrayList.remove(index);
	}

	/**
	 * returns the tasks added
	 * @return an arraylist of all the tasks in the current program
	 */
	public ArrayList<Task> getTaskArrayList() {
		return this.taskArrayList;
	}

	/**
	 * mark a task within the taskList as completed
	 * @param index index of the task to be marked
	 * @return the task object at specified index
	 */
	public Task markCompleted(Integer index) {
		Task task = taskArrayList.get(index);
		task.markCompleted();
		return task;
	}

	/**
	 * get the number of tasks in memory
	 * @return a Integer representing the number of tasks in the program currently
	 */
	public Integer getTaskListSize() {
		return taskArrayList.size();
	}

	/**
	 * converts the lists into a nice string representation of all the lists in the taskList object and also includes
	 * 		a numbering for each task from 1
	 */
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < taskArrayList.size(); i++) {
			output.append(String.format("%d.%s", i + 1, taskArrayList.get(i).toString()));
			if (i != taskArrayList.size() - 1) {
				output.append("\n");
			}
		}
		return output.toString();
	}
}
