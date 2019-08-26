package weomucat.duke.command.listener;

import weomucat.duke.exception.DukeException;

public interface DoneTaskCommandListener {
	void doneTaskCommandUpdate(int i) throws DukeException;
}
