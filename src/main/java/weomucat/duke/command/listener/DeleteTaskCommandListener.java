package weomucat.duke.command.listener;

import weomucat.duke.exception.DukeException;

public interface DeleteTaskCommandListener {
	void deleteTaskCommandUpdate(int i) throws DukeException;
}
