package duke;

import duke.frontend.UserInterface;

/**
 * The driver class to run user interface of Duke
 */
public class Duke {

	public UserInterface userInterface;

	private void run() {
		// initialize user inferface with input source
		userInterface = new UserInterface();
		// start
		try {
			userInterface.start();
		} catch (DukeException ex) {
			userInterface.displayError(ex);
		}
		// query UI if it can accept user input
		while (userInterface.isAcceptingInput()) {
			try {
			    // try to read input and then try to execute it
				userInterface.executeCommand(userInterface.readInput());
				// display output if successfully executed
				userInterface.displayOutput();
			} catch (duke.DukeException ex) {
				// display output if any error occurs
				userInterface.displayError(ex);
			}
		}
	}

	public static void main(String[] args) {
		new Duke().run();
	}
}
