import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
	private ArrayList<Task> taskList;
	
	public TaskList() {
		taskList = new ArrayList<Task>();
	}

	public void addTask(Task t) {
		taskList.add(t);
	}

	public void complete(int id) {
		taskList.get(id - 1).complete();
	}

	public Task remove(int id) {
		return taskList.remove(id);
	}

}
