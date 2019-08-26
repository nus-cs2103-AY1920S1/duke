package weomucat.duke.task;

import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.listener.DeleteTaskCommandListener;
import weomucat.duke.command.listener.DoneTaskCommandListener;
import weomucat.duke.command.listener.ListTaskCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.task.listener.AddTaskListener;
import weomucat.duke.task.listener.DeleteTaskListener;
import weomucat.duke.task.listener.DoneTaskListener;
import weomucat.duke.task.listener.ListTaskListener;

import java.util.ArrayList;

public class TaskList implements AddTaskCommandListener, DeleteTaskCommandListener, DoneTaskCommandListener, ListTaskCommandListener {
	private TaskListTasks tasks;
	private ArrayList<AddTaskListener> addTaskListeners;
	private ArrayList<DeleteTaskListener> deleteTaskListeners;
	private ArrayList<DoneTaskListener> doneTaskListeners;
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

	public void newListTaskListener(ListTaskListener listener) {
		this.listTaskListeners.add(listener);
	}

	@Override
	public void addTaskCommandUpdate(Task task) throws DukeException {
		// Add task to Tasks
		this.tasks.add(task);

		// Update AddTaskListeners
		for (AddTaskListener listener : addTaskListeners) {
			listener.addTaskUpdate(this.tasks, task);
		}
	}

	@Override
	public void deleteTaskCommandUpdate(int i) throws DukeException {
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
			throw new InvalidParameterException("That is not a valid index of a task.");
		}
	}

	@Override
	public void doneTaskCommandUpdate(int i) throws DukeException {
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
			throw new InvalidParameterException("That is not a valid index of a task.");
		}
	}

	@Override
	public void listTaskCommandUpdate() {
		// Update ListTaskListeners
		for (ListTaskListener listener : listTaskListeners) {
			listener.listTaskUpdate(this.tasks);
		}
	}
}
