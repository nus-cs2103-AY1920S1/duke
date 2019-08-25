import java.util.Scanner;

public class Ui {

	protected Scanner scanner = new Scanner(System.in);

	public void printLogo() {
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);
	}

	public void greetUser() {
		printLine();
		System.out.println("\t Hello! I'm Duke");
		System.out.println("\t What can I do for you?");
		printLine();
	}

	public String readCommand() {
		return scanner.nextLine();
	}

	public void printLine() {
		System.out.println("\t ----------------------------------------------------------");
	}

	public void printExitMessage() {
		System.out.println("\t Bye. Hope to see you again soon!");
	}

	public void printDeleteMessage(Task deletedTask, int size) {
		System.out.println("\t Noted. I've removed this task:");
		System.out.print("\t \t");
		System.out.println(deletedTask);
		System.out.println("\t Now you have " + size + (size == 1 ? " task " : " tasks ")
				+ "in the " + "list.");
	}

	public void printDoneMessage(Task doneTask) {
		System.out.println("\t Nice! I've marked this task as done:");
		System.out.print("\t \t");
		System.out.println(doneTask);
	}

	public void printAddTaskMessage(Task addedTask, int size) {
		System.out.println("\t Got it. I've added this task:");
		System.out.println("\t \t " + addedTask);
		System.out.println("\t Now you have " + size + (size == 1 ? " task " : " tasks ")
				+ "in the " + "list.");
	}

	public void printExceptionMessage(DukeException exception) {
		System.out.println("\t " + exception.getMessage());
	}

	public void printLoadingErrorMessage(DukeException exception) {
		printLine();
		printExceptionMessage(exception);
		printLine();
	}

	public void printMessage(String message) {
		System.out.println(message);
	}

	public void closeScanner() {
		scanner.close();
	}

}
