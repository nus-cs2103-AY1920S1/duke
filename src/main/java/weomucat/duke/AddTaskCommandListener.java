package weomucat.duke;

public interface AddTaskCommandListener {
	void addTaskCommandUpdate(Task task) throws DukeException;
}
