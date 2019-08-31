import java.util.Scanner;

public class Ui {
	Scanner scanner;
	String BORDER = "____________________________________________________________";
	String INTRO = BORDER + "\nHello! I'm Duke\nWhat can I do for you?\n" + BORDER;
	String OUTRO = BORDER + "\nBye. Hope to see you again soon!\n" + BORDER;
	String NOTASK = BORDER + "\nYou currently have no task!\n" + BORDER;
	String OUTPUTTASKLIST = BORDER + "\nHere are the tasks in your list:";
	String WRONGOP = BORDER + "\n☹ OOPS!!! Which task do you want to complete?\n" + BORDER;
	String DONEFORMAT = "OOPS!! Wrong format! Format: done [task number]";
	String MARKDONE = BORDER + "\nNice! I've marked this task as done:";
	String EMPTYINPUT = "☹ OOPS!!! The description cannot be empty.";
	String DEADLINEFORMAT = "OOPS!! Wrong format! Format: deadline [Task] /by [deadline]";
	String EVENTFORMAT = "OOPS!! Wrong format! Format: event [Task] /at [time]";
	String DELETEFORMAT = "OOPS!! Wrong format! Format: delete [task number]";
	String input;

	public Ui() {

		scanner = new Scanner(System.in);
	}

	public String getInput () {

		input = scanner.nextLine();

		return input;
	}

}
