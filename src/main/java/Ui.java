import java.util.Scanner;

/**
 * Stores the text for output and exception messages.
 */
public class Ui {
	Scanner scanner;
	public static final String BORDER = "____________________________________________________________";
	public static final String INTRO = BORDER + "\nHello! I'm Duke\nWhat can I do for you?\n" + BORDER;
	public static final String OUTRO = BORDER + "\nBye. Hope to see you again soon!\n" + BORDER;
	public static final String NO_TASK = BORDER + "\nYou currently have no task!\n" + BORDER;
	public static final String OUTPUT_TASK_LIST = BORDER + "\nHere are the tasks in your list:\n";
	public static final String WRONG_OP = BORDER + "\n OOPS!!! Which task do you want to complete?\n" + BORDER;
	public static final String DONE_FORMAT = "OOPS!! Wrong format! Format: done [task number]";
	public static final String MARK_DONE = BORDER + "\nNice! I've marked this task as done:\n";
	public static final String EMPTY_INPUT = "OOPS!!! The description cannot be empty.";
	public static final String DEADLINE_FORMAT = "OOPS!! Wrong format! Format: deadline [Task] /by [deadline]";
	public static final String EVENT_FORMAT = "OOPS!! Wrong format! Format: event [Task] /at [time]";
	public static final String DELETE_FORMAT = "OOPS!! Wrong format! Format: delete [task number]";

	public Ui() {

		scanner = new Scanner(System.in);
	}

}
