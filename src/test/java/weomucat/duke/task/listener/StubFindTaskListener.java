package weomucat.duke.task.listener;

import weomucat.duke.task.TaskListTasks;

public class StubFindTaskListener implements FindTaskListener {
	private TaskListTasks tasks;

	@Override
	public void findTaskUpdate(TaskListTasks tasks) {
		this.tasks = tasks;
	}

	public TaskListTasks getTasks() {
		return tasks;
	}
}
