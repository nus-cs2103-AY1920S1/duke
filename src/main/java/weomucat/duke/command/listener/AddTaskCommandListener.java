package weomucat.duke.command.listener;

import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;

public interface AddTaskCommandListener {
	void addTaskCommandUpdate(Task task) throws DukeException;
}
