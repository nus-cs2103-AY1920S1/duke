package duke.ui;
import duke.parser.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UiText {
    private final Scanner in;
    private final PrintStream out;

    public UiText() {
        this(System.in, System.out);
    }

    public UiText(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        return in.nextLine();

    }
    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        out.println("Hello from\n" + logo);
        out.println("Hello! I\'m Duke\n"
                + "What can I do for you?");

    }

    public void showLine() {
        out.println("________________________________________________");
    }
    public void echo(String msg) {
        out.println(msg);
    }

    public void leavingMsg() {
        out.println("Bye. Hope to see you again soon!");
    }

    public void printlnMsg(String msg) {
        out.println(msg);
    }

    public void showLoadingError() {
        out.println("Cannot load the file");
    }

    public void addedMsg(Task task) {
        out.println(String.format(
                "Got it. I\'ve added this task:\n  "
                        + task + "\nNow you have %s tasks in the list", Task.getNoOfTask()));
    }

    public void markedMsg(Task task) {
        out.println(
                String.format("Nice! I've marked this task as done:\n"
                        + "  " + task));
    }

    public void deleteMsg(Task task) {
        out.println(String.format(
                "Noted. I\'ve removed this task: \n  "
                        + task + "\nNow you have %d tasks in the list.", Task.getNoOfTask()));
    }

    public void findMsg(ArrayList<Task> tasks) {
        out.println("Here are the matching tasks in your list");
        int count = 0;
        for (Task task : tasks) {
            out.println(++count + "." + task.toString());
        }


    }
    public void unableToWriteFileError() {
        out.println("Unable to write the file");
    }

    public void showError(String msg) {
        out.println(msg);
    }


}
