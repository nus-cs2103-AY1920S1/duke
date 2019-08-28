package duke.ui;
import duke.parser.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with the interaction with the user.
 * Handle input and put put
 * @author Yang Shuting
 */
public class UiText {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Default constructor with standard I/O
     */
    public UiText() {
        this(System.in, System.out);
    }

    /**
     * Constructor that allows to choose the InputStream and PrintStream.
     * @param in InputStream of the choice.
     * @param out PrintStream of the choice.
     */
    public UiText(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Read the input from the chosen input.
     * @return  A line of string from the input.
     */
    public String readCommand() {
        return in.nextLine();

    }

    /**
     * Standard welcome message that will displayed when the user start the Chatbot.
     */
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


    /**
     * Divider between each action of the command.
     */
    public void showLine() {
        out.println("________________________________________________");
    }

    public void echo(String msg) {
        out.println(msg);
    }

    /**
     * Leaving message when the user input the leaving command.
     */
    public void leavingMsg() {
        out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print the string by chosen PrintStream.
     * @param msg String that to be desplay.
     */
    public void printlnMsg(String msg) {
        out.println(msg);
    }

    public void showLoadingError() {
        out.println("Cannot load the file");
    }

    /**
     * Message that will be displayed whenever a command has executed successfully.
     * @param task Task object that is added to the list.
     */
    public void addedMsg(Task task) {
        out.println(String.format(
                "Got it. I\'ve added this task:\n  "
                        + task + "\nNow you have %s tasks in the list", Task.getNoOfTask()));
    }

    /**
     * Message that will be displayed when a task has successfully marked done.
     * @param task Task object that marked done.
     */
    public void markedMsg(Task task) {
        out.println(
                String.format("Nice! I've marked this task as done:\n"
                        + "  " + task));
    }

    /**
     * Message displayed when a task is deleted from the list.
     *
     * @param task Task object that was deleted.
     */
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
