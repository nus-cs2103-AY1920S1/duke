package utilities;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author bakwxh
 * @version 0.1
 */
public class TaskList {
	/**
	 * Array list for memory.
	 */
	private ArrayList<Task> memory;
	private Stack<Task> lastTasks;
	private Stack<String> lastCommands;

	/** Constructor for when there is previously saved data.
	 * @param inputMemory Previous memory.
	 */
	public TaskList(ArrayList<Task> inputMemory) {
		lastTasks = new Stack<>();
		lastCommands = new Stack<>();
		memory = inputMemory;
	}

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
	public String listLatest() { return memory.get(memory.size() - 1).showTask(); }

	/**
	 * Adds a task into memory, updates the last known command and task.
	 * @param t Added task.
	 */
	public void addTask(Task t) {
		memory.add(t);
		lastTasks.push(t);
		lastCommands.push("add");
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
			lastTasks.push(removed);
			lastCommands.push("delete " + index);
			return removed;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DukeException("Please indicate which task to delete.");
		} catch (IndexOutOfBoundsException e) {
			throw new DukeException("That task does not exist!");
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
			lastTasks.push(memory.get(index));
			lastCommands.push("done " + index);
		} catch (IndexOutOfBoundsException e) {
			throw new DukeException("Indicated task does not exist.");
		}
	}

	public String undoTask() throws DukeException {
		try {
			String result = "";
			String lastCommand = lastCommands.pop();
			String command = lastCommand.split(" ")[0];
			Task lastTask = lastTasks.pop();
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
		} catch (EmptyStackException e) {
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
