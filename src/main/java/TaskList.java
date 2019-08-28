import java.util.ArrayList;

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

	public void addTask(Task task) {

		tasks.add(task);
		counter++;
	}

	public void deleteTask (int index) {

		tasks.remove(index);
		counter--;
	}

	public int getCounter () {
		return counter;
	}

	public Task getTask(int index) {
		return tasks.get(index);
	}

	public ArrayList<Task> getTaskList() {
		return tasks;
	}
}
