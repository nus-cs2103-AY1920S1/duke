package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

/**
 * Represents a user interface.
 * A <code>Ui</code> object handles interaction between chat bot and user through printing messages and reading
 * user inputs.
 */
public class Ui {

	protected Scanner scanner = new Scanner(System.in);

	/**
	 * Prints the welcome logo.
	 */
	public void printLogo() {
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);
	}

	/**
	 * Prints a message to greet the user.
	 */
	public void greetUser() {
		printLine();
		System.out.println("\t Hello! I'm Duke");
		System.out.println("\t What can I do for you?");
		printLine();
	}

	/**
	 * Reads a line of command given by the user.
	 * @return The unprocessed raw command.
	 */
	public String readCommand() {
		return scanner.nextLine();
	}

	/**
	 * Prints a line.
	 */
	public void printLine() {
		System.out.println("\t ----------------------------------------------------------");
	}

	/**
	 * Prints a message when the program exitss.
	 */
	public void printExitMessage() {
		System.out.println("\t Bye. Hope to see you again soon!");
	}

	/**
	 * Prints a message when a task is deleted.
	 * @param deletedTask The <code>Task</code> that is deleted.
	 * @param size Current size of the <code>TaskList</code>.
	 */
	public void printDeleteMessage(Task deletedTask, int size) {
		System.out.println("\t Noted. I've removed this task:");
		System.out.print("\t \t");
		System.out.println(deletedTask);
		System.out.println("\t Now you have " + size + (size == 1 ? " task " : " tasks ")
				+ "in the " + "list.");
	}

	/**
	 * Prints a message when a task is marked as done.
	 * @param doneTask The <code>Task</code> that has been completed.
	 */
	public void printDoneMessage(Task doneTask) {
		System.out.println("\t Nice! I've marked this task as done:");
		System.out.print("\t \t");
		System.out.println(doneTask);
	}

	/**
	 * Prints a message when a task is added.
	 * @param addedTask The <code>Task</code> that is added.
	 * @param size Current size of the <code>TaskList</code>.
	 */
	public void printAddTaskMessage(Task addedTask, int size) {
		System.out.println("\t Got it. I've added this task:");
		System.out.println("\t \t " + addedTask);
		System.out.println("\t Now you have " + size + (size == 1 ? " task " : " tasks ")
				+ "in the " + "list.");
	}

	/**
	 * Prints a message when an exception occurs.
	 * @param exception The <code>Exception</code> that has occurred.
	 */
	public void printExceptionMessage(DukeException exception) {
		System.out.println("\t " + exception.getMessage());
	}

	/**
	 * Prints a message when the file in the file path cannot be loaded.
	 * @param exception The <code>Exception</code> that has occurred.
	 */
	public void printLoadingErrorMessage(DukeException exception) {
		printLine();
		printExceptionMessage(exception);
		printLine();
	}

	/**
	 * Prints a customizable message.
	 * @param message Message that is to be printed.
	 */
	public void printMessage(String message) {
		System.out.println(message);
	}

	/**
	 * Closes the scanner.
	 */
	public void closeScanner() {
		scanner.close();
	}

}
