package duke.component;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidInputException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Ui class deals with the interactions with the user.
 */
public class Ui {
	
	/**
	 * Class constructor
	 */
	public Ui() {
	}
	
	/**
	 * This method prints the welcome message for the user when they first
	 * enter the program. It is always shown to signal the start of the program.
	 */
	public void showWelcome() {
		System.out.println("Hello! I'm Duke \nWhat can I do for you?");
	}
	
	/**
	 * This method reads the input by the user.
	 * @return string input as keyed in by the user.
	 */
	public String readCommand() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}
	
	/**
	 * This method prints the error message accordingly to the different exceptions.
	 *
	 * @param Exception e and handles the exceptions accordingly.
	 */
	public void showError(Exception e) {
		if (e instanceof InvalidInputException) {
			System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
		} else if (e instanceof EmptyDescriptionException) {
			System.out.println(String.format("OOPS!!! The description of a %s cannot be empty.", e.getMessage()));
		} else if (e instanceof InvalidDescriptionException) {
			System.out.println(String.format("OOPS!!! Invalid input! Make sure your %s has a description and required" +
					" data after /at for Event or /by for Deadline.\n", e.getMessage()));
		} else if (e instanceof ParseException) {
			System.out.println(String.format("Please write your deadline/event date in this format: dd/MM/yyyy HH:mm," +
					" example: 02/08/2019 14:30\n", e.getMessage()));
		} else {
			System.out.println(e.getMessage());
		}
	}
}
