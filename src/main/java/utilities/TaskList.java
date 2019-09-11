package utilities;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author bakwxh
 * @version 0.1
 */
public class TaskList {
	/**
	 * Array list for memory.
	 */
	private ArrayList<Task> memory;

	/** Constructor for when there is previously saved data.
	 * @param inputMemory Previous memory.
	 */
	public TaskList(ArrayList<Task> inputMemory) {
		memory = inputMemory;
	}

	private Task lastTask;
	private String lastCommand;

	/**
	 * Empty constructor for when there is no previous data.
	 */
	public TaskList() {
		memory = new ArrayList<>();
	}

	/**
	 * Lists out tasks in memory.
	 * @return String of tasks stored.
	 */
	public String list() {
		String result = "";
		int counter = 1;
		for (Task t : memory) {
			result += " " + counter + "." + t.showTask() + "\n";
			counter++;
		}

		return result;
	}
	
	/**
	 * Lists tasks with keyword in them.
	 * @param keyword The keyword.
	 * @return String of list of tasks with keyword contained.
	 */
	public String keywordList(String keyword) {
		String result = "";
		int counter = 1;
		for (Task t : memory) {
			if (t.getDesc().contains(keyword)) {
				result += " " + counter + "." + t.showTask() + "\n";
				counter++;
			}
		}

		return result;
	}

	/**
	 * Lists the newest task in memory.
	 * @return Newest task.
	 */
	public String listLatest() {
		return memory.get(memory.size() - 1).showTask();
	}

	/**
	 * Adds a task into memory, updates the last known command and task.
	 * @param t Added task.
	 */
	public void addTask(Task t) {
		memory.add(t);
		lastTask = t;
		lastCommand = "add";
	}

	/**
	 * Deletes task.
	 * @param index Index of task in memory.
	 * @return Deleted task.
	 * @throws DukeException Exceptions.
	 */
	public Task deleteTask(int index) throws DukeException {
		try {
			Task removed = memory.get(index);
			memory.remove(index);
			lastTask = removed;
			lastCommand = "delete " + index;
			return removed;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DukeException("Please indicate which task to delete.");
		}
	}

	/**
	 * Marks task as done.
	 * @param index Index of done task.
	 * @throws DukeException Exception.
	 */
	public void doneTask(int index) throws DukeException {
		try {
			memory.get(index).markAsDone();
			lastTask = memory.get(index);
			lastCommand = "done " + index;
		} catch (IndexOutOfBoundsException e) {
			throw new DukeException("Indicated task does not exist.");
		}
	}

	public String undoTask() throws DukeException {
		System.out.println("CALLED " + lastCommand);
		try {
			String result = "";
			String command = lastCommand.split(" ")[0];
			switch (command) {
				case "add":
					result = " Got it! The following task has been removed:\n   ";
					memory.remove(memory.size() - 1);
					break;
				case "delete":
					result = " Got it! The following task has been recovered:\n   ";
					int deleteIndex = Integer.parseInt(lastCommand.split(" ")[1]);
					memory.add(deleteIndex, lastTask);
					break;
				case "done":
					result = " Got it! The following command has been undone:\n   ";
					int doneIndex = Integer.parseInt(lastCommand.split(" ")[1]);
					memory.get(doneIndex).markAsUndone();
					break;
			}
			lastCommand = null;
			result += lastTask.showTask() + "\n";
			return result;
		} catch (NullPointerException e) {
			throw new DukeException("No task to undo!");
		}
	}

	/**
	 * Retrieves memory array list.
	 * @return Memory array list.
	 */
	public ArrayList<Task> getMemory() {
		return this.memory;
	}
}
