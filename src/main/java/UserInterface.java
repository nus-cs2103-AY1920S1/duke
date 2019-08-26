import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String LINE = "____________________________________________________________";
    private static final String LINE_PREFIX = "     ";
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke\n" + LINE_PREFIX + "What can I do for you?\n" + LINE;
    private static final String MESSAGE_ADDED = "Got it. I've added this task:";
    private static final String MESSAGE_DELETED = "Noted. I've removed this task:";
    private static final String MESSAGE_MARK_DONE = "Nice! I've marked this task as done:";
    private static final String MESSAGE_SHOW_TASK_LIST = "Here are the tasks in your list:";
    private static final String MESSAGE_SHOW_TASK_SIZE = "Now you have %1$s tasks in the list.";
    public final String MESSAGE_INVALID_COMMAND_FORMAT = "I'm sorry, but I don't know what that means :-(";
    public final String MESSAGE_INVALID_TODO_FORMAT = "The description of a todo cannot be empty.";
    public final String MESSAGE_INVALID_DEADLINE_FORMAT =
            "Deadlines must have /by and cannot be empty. E.g. deadline return book /by 2/12/2019 1800";
    public final String MESSAGE_INVALID_EVENT_FORMAT =
            "Events must have /at and cannot be empty. E.g. event project meeting /at 2/12/2019 1800";
    public final String MESSAGE_INVALID_TASK_INDEX = "Invalid task number. Task numbers follow 1-based indexing.";
    public static final String MESSAGE_ERROR_MISSING_STORAGE_FILE = "Missing storage file: %1$s";
    public static final String MESSAGE_ERROR_READING_FROM_FILE = "Error when reading file";
    public final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date. Follow d/M/yyyy HHmm format. E.g. 2/12/2019 1800";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public String readLine() {
        String nextLine = SCANNER.nextLine().strip();
        return nextLine;
    }

    public void echo(String inputLine) {
        System.out.println(inputLine);
    }

    public void showToUser(String line) {
        System.out.println(LINE_PREFIX + line);
    }

    public void showLine() {
        showToUser(LINE);
    }

    public void showWelcomeMessage() {
        showToUser(MESSAGE_WELCOME);
    }

    public void exitProgram() {
        showToUser(MESSAGE_BYE);
        System.exit(0);
    }

    public void showAddition(Task task) {
        showToUser(MESSAGE_ADDED);
        showToUser(" " + task);
    }

    public void showDeletion(Task task) {
        showToUser(MESSAGE_DELETED);
        showToUser(" " + task);
    }

    public void showTaskList(List<String> taskNames) {
        showToUser(MESSAGE_SHOW_TASK_LIST);
        for (String taskName : taskNames) {
            showToUser(taskName);
        }
    }

    public void showMarkDone(Task task) {
        showToUser(MESSAGE_MARK_DONE);
        showToUser(" " + task);
    }

    public void showTaskSize(TaskList taskList) {
        showToUser(String.format(MESSAGE_SHOW_TASK_SIZE, taskList.size()));
    }

    public void showExceptionMessage(String message) {
        showToUser("☹ OOPS!!! " + message);
    }
}
