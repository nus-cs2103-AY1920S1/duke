package weomucat.duke.task;

import weomucat.duke.command.listener.*;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidTaskIndexException;
import weomucat.duke.task.listener.*;

import java.util.ArrayList;

public class TaskList implements AddTaskCommandListener, DeleteTaskCommandListener, DoneTaskCommandListener, FindTaskCommandListener, ListTaskCommandListener {
	private TaskListTasks tasks;
	private ArrayList<AddTaskListener> addTaskListeners;
	private ArrayList<DeleteTaskListener> deleteTaskListeners;
	private ArrayList<DoneTaskListener> doneTaskListeners;
	private ArrayList<FindTaskListener> findTaskListeners;
	private ArrayList<ListTaskListener> listTaskListeners;

	public TaskList() {
		this.tasks = new TaskListTasks();
		init();
	}

	public TaskList(TaskListTasks tasks) {
		this.tasks = tasks;
		init();
	}

	private void init() {
		this.addTaskListeners = new ArrayList<>();
		this.deleteTaskListeners = new ArrayList<>();
		this.doneTaskListeners = new ArrayList<>();
		this.findTaskListeners = new ArrayList<>();
		this.listTaskListeners = new ArrayList<>();
	}

	public void newAddTaskListener(AddTaskListener listener) {
		this.addTaskListeners.add(listener);
	}

	public void newDeleteTaskListener(DeleteTaskListener listener) {
		this.deleteTaskListeners.add(listener);
	}

	public void newDoneTaskListener(DoneTaskListener listener) {
		this.doneTaskListeners.add(listener);
	}

	/**
	 * Add a FindTaskListener to the TaskList.
	 * When findTask is called, this listener will be notified.
	 * @param listener findTask listener
	 */
	public void newFindTaskListener(FindTaskListener listener) {
		this.findTaskListeners.add(listener);
	}

	public void newListTaskListener(ListTaskListener listener) {
		this.listTaskListeners.add(listener);
	}

	public void addTask(Task task) throws DukeException {
		// Add task to Tasks
		this.tasks.add(task);

		// Update AddTaskListeners
		for (AddTaskListener listener : addTaskListeners) {
			listener.addTaskUpdate(this.tasks, task);
		}
	}

	public void deleteTask(int i) throws DukeException {
		try {
			// Get task from tasks
			Task task = this.tasks.get(i);

			// Remove task
			this.tasks.remove(i);

			// Update DeleteTaskListeners
			for (DeleteTaskListener listener : deleteTaskListeners) {
				listener.deleteTaskUpdate(this.tasks, task);
			}
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidTaskIndexException();
		}
	}

	public void doneTask(int i) throws DukeException {
		try {
			// Get task from tasks
			Task task = this.tasks.get(i);

			// Set task to done
			task.setDone(true);

			// Update DoneTaskListeners
			for (DoneTaskListener listener : doneTaskListeners) {
				listener.doneTaskUpdate(this.tasks, task);
			}
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidTaskIndexException();
		}
	}

	/**
	 * Searches for a keyword in all tasks.
	 * Notify listeners tasks which description matches the keyword.
	 */
	public void findTask(String keyword) {
		TaskListTasks result = new TaskListTasks();

		for (Task task : this.tasks) {
			if (task.getDescription().contains(keyword)) {
				result.add(task);
			}
		}

		// Update FindTaskListeners
		for (FindTaskListener listener : findTaskListeners) {
			listener.findTaskUpdate(result);
		}
	}

	public void listTask() {
		// Update ListTaskListeners
		for (ListTaskListener listener : listTaskListeners) {
			listener.listTaskUpdate(this.tasks);
		}
	}

	@Override
	public void addTaskCommandUpdate(Task task) throws DukeException {
		addTask(task);
	}

	@Override
	public void deleteTaskCommandUpdate(int i) throws DukeException {
		deleteTask(i);
	}

	@Override
	public void doneTaskCommandUpdate(int i) throws DukeException {
		doneTask(i);
	}

	@Override
	public void findTaskCommandUpdate(String keyword) {
		findTask(keyword);
	}

	@Override
	public void listTaskCommandUpdate() {
		listTask();
	}
}
