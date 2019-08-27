package duke.ui;

import duke.common.Message;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String LINE = "____________________________________________________________";
    public static final String LINE_PREFIX = "     ";
    public static final String LOGO = " ____        _        \n"
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
        showToUser(String.format(Message.MESSAGE_WELCOME, LINE_PREFIX, LINE));
    }

    public void exitProgram() {
        showToUser(Message.MESSAGE_BYE);
        System.exit(0);
    }

    public void showAddition(Task task) {
        showToUser(Message.MESSAGE_ADDED);
        showToUser(" " + task);
    }

    public void showDeletion(Task task) {
        showToUser(Message.MESSAGE_DELETED);
        showToUser(" " + task);
    }

    public void showTaskList(List<String> taskNames) {
        showToUser(Message.MESSAGE_SHOW_TASK_LIST);
        for (String taskName : taskNames) {
            showToUser(taskName);
        }
    }

    public void showMarkDone(Task task) {
        showToUser(Message.MESSAGE_MARK_DONE);
        showToUser(" " + task);
    }

    public void showTaskSize(TaskList taskList) {
        showToUser(String.format(Message.MESSAGE_SHOW_TASK_SIZE, taskList.size()));
    }

    public void showExceptionMessage(String message) {
        showToUser("☹ OOPS!!! " + message);
    }
}
