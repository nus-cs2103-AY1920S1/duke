import java.util.function.Consumer;
import java.util.ArrayList;

import java.text.ParseException;


class TaskList {
	protected ArrayList<Task> list;

	public TaskList() {
		this.list = new ArrayList<Task>();
	}

	public TaskList(ArrayList<AddCommand> commands) throws DukeException, ParseException {
		this.list = new ArrayList<>();
		for (AddCommand ac: commands) {
			addTask(ac.getTask());
		}
	}

	public ArrayList<Task> getList() {
		return list;
	}

	/**
	 * Adds new tasks to list.
	 * @param Task task
	 */
	public void addTask(Task task) {
		list.add(task);
	}
}