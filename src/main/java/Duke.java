public class Duke {

	public UserInterface userInterface;

	public void run() {
		// initialize user inferface with input source
		userInterface = new UserInterface(System.in);
		// start
		try {
			userInterface.start();
		} catch (DukeException ex) {
			userInterface.displayError(ex);
		}
		// query UI if it can accept user input
		while (userInterface.isAcceptingInput()) {
			try {
				// wait for user input
				Command command = userInterface.readInput();
				// process input
				userInterface.executeCommand(command);
				// display output
				userInterface.displayOutput();
			} catch (DukeException ex) {
				// display output if any error occurs
				userInterface.displayError(ex);
			}
		}
	}

	public static void main(String[] args) {
		new Duke().run();
	}
}
