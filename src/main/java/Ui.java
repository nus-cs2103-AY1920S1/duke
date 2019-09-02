import java.util.Scanner;

/**
 * Stores the text for output and exception messages.
 */
public class Ui {
	Scanner scanner;
	String BORDER = "____________________________________________________________";
	String INTRO = BORDER + "\nHello! I'm Duke\nWhat can I do for you?\n" + BORDER;
	String OUTRO = BORDER + "\nBye. Hope to see you again soon!\n" + BORDER;
	String NO_TASK = BORDER + "\nYou currently have no task!\n" + BORDER;
	String OUTPUT_TASK_LIST = BORDER + "\nHere are the tasks in your list:";
	String WRONG_OP = BORDER + "\n☹ OOPS!!! Which task do you want to complete?\n" + BORDER;
	String DONE_FORMAT = "OOPS!! Wrong format! Format: done [task number]";
	String MARK_DONE = BORDER + "\nNice! I've marked this task as done:";
	String EMPTY_INPUT = "☹ OOPS!!! The description cannot be empty.";
	String DEADLINE_FORMAT = "OOPS!! Wrong format! Format: deadline [Task] /by [deadline]";
	String EVENT_FORMAT = "OOPS!! Wrong format! Format: event [Task] /at [time]";
	String DELETE_FORMAT = "OOPS!! Wrong format! Format: delete [task number]";
	String input;

	public Ui() {

		scanner = new Scanner(System.in);
	}

	public String getInput() {

		input = scanner.nextLine();

		return input;
	}

}
