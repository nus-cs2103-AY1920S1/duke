package duke;

import duke.task.Task;

import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintStream;

public class Ui {

    protected static final String HORIZONTAL_LINE = "____________________________________________________________";
    protected static final String INDENTATION = "    ";
    protected static final String LOGO =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    protected final Scanner in;
    protected final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void output(String... messages) {
        out.println(INDENTATION + HORIZONTAL_LINE);
        for (String message : messages) {
            out.print(INDENTATION + message.replace("\n", "\n" + INDENTATION));
        }
        out.println(HORIZONTAL_LINE + "\n");
    }

    public String input() {
        return in.nextLine();
    }

    public void showTaskList(TaskList taskList) {
        output("Here are the tasks in your list:\n" + taskList.list());
    }

    public void showDoneMessage(Task task) {
        output("Nice! I've marked this task as done:\n" + task + "\n");
    }

    public void showDeleteMessage(int size, Task task) {
        if (size == 1)
            output("Noted. I've removed this task: \n" + task + "\n" +  "Now you have "
                    + size + " task in the list.\n");
        else
            output("Noted. I've removed this task: \n" + task + "\n" +  "Now you have "
                    + size + " tasks in the list.\n");
    }

    public void showAddMessage(int size, Task task) {
        if (size == 1)
            output("Got it. I've added this task: \n" + task + "\n" +  "Now you have "
                    + size + " task in the list.\n");
        else
            output("Got it. I've added this task: \n" + task + "\n" +  "Now you have "
                    + size + " tasks in the list.\n");
    }

    public void showWelcomeMessage() {
        output("Hello from\n" + LOGO + "What can I do for you?\n");
    }

    public void showByeMessage() {
        output("Bye. Hope to see you again soon!\n");
    }

    public void showFindMessage(TaskList taskList, String keyword) {
        output("Here are the matching tasks in your list:\n" + taskList.find(keyword));
    }

    public void showLoadingError() {
        output("File not found.\n");
    }

    public void showError(String error) {
        output(error);
    }

}
