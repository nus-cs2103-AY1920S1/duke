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

	/**
	 * List by matching description of exisitng list of tasks against keyword
	 * @param keyword
	 */
	public void listMatch(String keyword, Ui ui) {
		int counter = 1;
		for (Task task: this.list) {
			if (task.getDescription().contains(keyword)) {
				ui.print("Here are the matching tasks in your list:");
				ui.print(counter + "." + task);
				counter++;
			}
		}
	}
}