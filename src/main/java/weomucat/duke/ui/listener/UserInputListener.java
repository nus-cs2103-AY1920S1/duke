package weomucat.duke.ui.listener;

import weomucat.duke.exception.DukeException;

public interface UserInputListener {
	void userInputUpdate(String userInput) throws DukeException;
}
