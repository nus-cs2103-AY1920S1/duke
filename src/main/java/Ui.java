import javafx.application.Platform;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Scanner;

class Ui {
	private static SimpleDateFormat formatterIn = new SimpleDateFormat("d/M/yyyy HHmm");
	private static SimpleDateFormat formatterOut = new SimpleDateFormat("d MMM yyyy ha");

	private Scanner scanner;
	private StringBuilder builder = new StringBuilder();

	public Ui() {
		this.scanner = new Scanner(System.in);
	}

	public void showWelcome() {
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";

		print(logo);
		print("Hello! I'm Duke\nWhat can I do for you?");
	}

	public String readWord() {
		return scanner.next().trim();
	}

	/**
	 * @return Entire line of command
	 */
	public String readCommand() {
		return scanner.nextLine().trim();
	}

	@Deprecated
	public void showLine() {
		System.out.println("_______");
	}

	public void print(String msg) {
		builder.append(msg);
	}

	public void showError(String msg) {
		print(msg);
	}

	public void showLoadingError() {
		print("Loading error! New file created.");
	}

	public void exit() {
		// scanner.close();
		print("Bye. Hope to see you again soon!");
		Platform.exit();
	}

	public String out() {
		String output = builder.toString();
		builder = new StringBuilder();
		return output;
	}
}