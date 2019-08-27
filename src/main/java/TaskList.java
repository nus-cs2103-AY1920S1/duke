import java.util.ArrayList;

public class TaskList {
	private ArrayList<Task> taskArrayList;

	public TaskList() {
		taskArrayList = new ArrayList<>();
	}

	public TaskList(ArrayList<Task> taskArrayList) {
		this.taskArrayList = taskArrayList;
	}

	public Task addTask(Task task) {
		taskArrayList.add(task);
		return task;
	}

	public Task removeTask(int index) {
		return this.taskArrayList.remove(index);
	}

	public ArrayList<Task> getTaskArrayList() {
		return this.taskArrayList;
	}

	public Task markCompleted(Integer index) {
		Task task = taskArrayList.get(index);
		task.markCompleted();
		return task;
	}

	public Integer getTaskListSize() {
		return taskArrayList.size();
	}

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
