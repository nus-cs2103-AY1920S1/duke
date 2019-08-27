import java.util.Scanner;

/**
 * Represents the interaction between Duke and the User. Both in reading the input and displaying messages / errors
 * 		of the program
 */
public class Ui {
	private Scanner scanner;

	/**
	 * Default constructor for UI, initializes a scanner object to read user inputs.
	 */
	public Ui() {
		scanner = new Scanner(System.in);
	}

	/**
	 * show errors from attempting to load tasks from the archival file
	 * @param ex exception thrown from loading tasks from the archival file
	 */
	public void showLoadingErrors(Exception ex) {
		System.out.println(ex.getMessage());
	}

	/**
	 * show the welcome message to the user
	 */
	public void showWelcome() {
		System.out.println("Welcome to Duke. What can I do for you today?");
	}

<<<<<<< HEAD
	/**
	 * Displays messages to the user
	 * @param toShow the string to be displayed to the user
	 */
=======
>>>>>>> A-CodingStandard
	public void displayLine(String toShow) {
		System.out.println(toShow);
	}

	/**
	 * simply display a break line to organise the outputs
	 */
	public void showBreakLine() {
		System.out.println("------------------------------------------------------------------------------");
	}

	/**
	 * readCommand takes in the entire line of user input
	 * @return the fullCommand input by the user
	 */
	public String readCommand() {
		return scanner.nextLine();
	}

	/**
	 * show the exit message for duke. This method is run when the user closes the program through the bye command
	 */
	public void showExitMessage() {
		System.out.println("Bye. Hope to see you again soon!");
	}

	/**
	 * display the error messsages thrown from the duke program
	 * @param errorMessage a string representation of the error thrown from Duke
	 */
	public void showError(String errorMessage) {
		System.out.println(errorMessage);
	}

	/**
	 * displays to the user that the task was succesfully added to the taskList
	 * @param task the task that was added
	 * @param taskList the taskList representing all tasks added. Required to calculate the total number of tasks in
	 *                 memory
	 */
	public static void alertLatestTaskAdded(Task task, TaskList taskList) {
		System.out.println("Got it. I've added this task: ");
		System.out.println(task);
		System.out.printf("Now you have %d tasks in the list.\n", taskList.getTaskListSize());
	}
}
