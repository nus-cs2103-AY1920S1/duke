package seedu.duke.Ui;

import seedu.duke.DukeException;
import seedu.duke.TaskList.TaskList;
import seedu.duke.TaskList.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static final String LINE_PREFIX = "| ";

    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "____________________________________________________________";

    private final Scanner in;
    private final  PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readCommand() throws DukeException {
        String fullInputLine = in.nextLine();

        while (shoudIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }
        return fullInputLine;
    }

    private boolean shoudIgnore(String fullInputLine) {
        return fullInputLine.trim().isEmpty();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showToUser(DIVIDER, "Hello! I'm Duke\n" + "What can I do for you?", DIVIDER);
    }

    public void showLine() {
        showToUser(DIVIDER);
    }

    public void showGoodbye() {
        showToUser(DIVIDER, "Bye! Hope to see you again soon!", DIVIDER);
    }

    public void showAddedMessage(TaskList taskList, Task task) {
        showToUser("Got it. I've added this task:");
        showToUser(task.toActionString());
        showToUser("Now you have " + taskList.list.size()
                + " tasks in the list");
    }

    public void showDeleteMessage(TaskList taskList, Task task) {
        showToUser("Noted. I've removed this task: ");
        showToUser(task.toActionString());
        showToUser("Now you have " + (taskList.list.size() - 1)
                + " tasks in the list.");
    }

    public void showDoneMessage(Task task) {
        showToUser("Nice! I've marked this task as done:");
        showToUser(task.toActionString());
    }

    public void showError(String error) {
        showToUser(error);
    }

    public void showToUser (String... message) {
        for (String m: message) {
            out.println(m.replace("\n", LS));
        }
    }

    public void showLoadingError() {
        showToUser("There is something wrong.");
    }

    public void showListMessage(TaskList taskList) {
        showToUser("Here are the tasks in your list:");
        for(int i = 1;i <= taskList.list.size();i++) {
            showToUser(i + "."
                    + taskList.list.get(i - 1).toActionString());
        }
    }

    public void showFindMessage(ArrayList<Task> result) {
        showToUser("Here are the matching tasks in your list:");
        for (Task task: result) {
            showToUser(task.toActionString());
        }
    }

}

