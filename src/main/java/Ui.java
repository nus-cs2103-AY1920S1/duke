import java.util.Scanner;

public class Ui {
	private Scanner scanner;

	public Ui() {
		scanner = new Scanner(System.in);
	}

	public void showLoadingErrors(Exception ex) {
		System.out.println(ex.getMessage());
	}

	public void showWelcome() {
		System.out.println("Welcome to Duke. What can I do for you today?");
	}

	public String next() {
		return scanner.next();
	}

	public String nextLine() {
		return scanner.nextLine();
	}

	public void displayLine(String toShow) {
		System.out.println(toShow);
	}

	public void showBreakLine() {
		System.out.println("------------------------------------------------------------------------------");
	}

	public String readCommand() {
		return scanner.nextLine();
	}

	public void showExitMessage() {
		System.out.println("Bye. Hope to see you again soon!");
	}

	public void showError(String errorMessage) {
		System.out.println(errorMessage);
	}

	public boolean hasNext() {
		return scanner.hasNext();
	}

	public boolean hasNextLine() {
		return scanner.hasNextLine();
	}

	public static void alertLatestTaskAdded(Task task, TaskList taskList) {
		System.out.println("Got it. I've added this task: ");
		System.out.println(task);
		System.out.printf("Now you have %d tasks in the list.\n", taskList.getTaskListSize());
	}
}
