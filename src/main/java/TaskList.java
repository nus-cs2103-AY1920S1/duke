import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

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

	public TaskList findByKeyword(String keyword) {
		ArrayList<Task> output = new ArrayList<>();
		for (Task task : this.taskArrayList) {
			if (task.getTaskName().contains(keyword)) {
				output.add(task);
			}
		}
		return new TaskList(output);
	}

	static class dateComparator implements Comparator<Task> {
		public int compare(Task task1, Task task2) {
			LocalDateTime task1DateTime = task1.getDateTime();
			LocalDateTime task2DateTime = task2.getDateTime();
			if (task1DateTime == null && task2DateTime != null) {
				return 1;
			} else if (task2DateTime == null && task1DateTime != null) {
				return -1;
			} else if (task1DateTime == null && task2DateTime == null) {
				return 0;
			} else {
				return task1DateTime.compareTo(task2DateTime);
			}
		}
	}

	static class nameComparator implements Comparator<Task> {
		public int compare(Task task1, Task task2) {
			String task1Name = task1.getTaskName();
			String task2Name = task2.getTaskName();
			if (task1Name.isEmpty() && !task2Name.isEmpty()) {
				return 1;
			} else if (task2Name.isEmpty() && !task1Name.isEmpty()) {
				return -1;
			} else if (task1Name.isEmpty() && task2Name.isEmpty()) {
				return 0;
			} else {
				return task1Name.compareTo(task2Name);
			}
		}
	}

	public TaskList sortByDate() {
		this.taskArrayList.sort(new dateComparator());
		return this;
	}

	public TaskList sortByName() {
		this.taskArrayList.sort(new nameComparator());
		return this;
	}
}
