import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String LINE = "____________________________________________________________";
    private static final String LINE_PREFIX = "     ";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public String readLine() {
        String nextLine = SCANNER.nextLine().strip();
        System.out.println(nextLine);
        return nextLine;
    }

    public void showToUser(String line) {
        System.out.println(LINE_PREFIX + line);
    }

    public void showLine() {
        showToUser(LINE);
    }

    public void showWelcomeMessage() {
        showToUser("Hello! I'm Duke");
        showToUser("What can I do for you?");
        showLine();
    }

    public void exitProgram() {
        showToUser(MESSAGE_BYE);
    }

    public void showAddition(Task task) {
        showToUser("  added: " + task);
    }

    public void showTaskList(List<String> taskNames) {
        for (String taskName : taskNames) {
            showToUser(taskName);
        }
    }
}