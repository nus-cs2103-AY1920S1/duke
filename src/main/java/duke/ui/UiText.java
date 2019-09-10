package duke.ui;

import duke.task.Task;

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
     * Prints the string by chosen PrintStream.
     * @param msg String that to be display.
     */

    public void printlnMsg(String msg) {
        assert msg.trim().length() > 0 : msg;
        out.println(msg);
    }

    /**
     * Standard welcome message that will displayed when the user start the Chatbot.
     */
    public static String greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String msg = "Hello from\n" + logo + "\nHello! I\'m Duke\n"
                + "What can I do for you?";
        return msg;

    }


    /**
     * Divider between each action of the command.
     */
    public static String showLine() {
        return "________________________________________________";
    }

    public void echo(String msg) {
        out.println(msg);
    }

    /**
     * Leaving message when the user input the leaving command.
     */
    public static String leavingMsg() {
        return "Bye. Hope to see you again soon!";
    }


    public static String loadingError() {
        return "Cannot load the file";
    }

    /**
     * Message that will be displayed whenever a command has executed successfully.
     * @param task Task object that is added to the list.
     */
    public static String addedMsg(Task task) {
        return String.format(
                "Got it. I\'ve added this task:\n  "
                        + task + "\nNow you have %s tasks in the list", Task.getNoOfTask());
    }

    /**
     * Message that will be displayed when a task has successfully marked done.
     * @param task Task object that marked done.
     */

    public static String markedMsg(Task task) {
        assert task.getStatusBit() == 1 : task;
        return String.format("Nice! I've marked this task as done:\n"
                        + "  " + task);
    }

    /**
     * Message displayed when a task is deleted from the list.
     *
     * @param task Task object that was deleted.
     */

    public static String deleteMsg(Task task) {
        return String.format(
                "Noted. I\'ve removed this task: \n  "
                        + task + "\nNow you have %d tasks in the list.", Task.getNoOfTask());
    }

    public static String findMsg(ArrayList<Task> tasks) {
        assert tasks.size() > 0;
        StringBuilder output = new StringBuilder();
        String title = "Here are the matching tasks in your list\n";
        output.append(title);
        int count = 0;
        for (Task task : tasks) {
            String result = ++count + "." + task.toString() + "\n";
            output.append(result);
        }
        return output.toString();
    }

    public static String listingMsg(ArrayList<Task> tasks) {
        assert tasks.size() > 0;
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        boolean isFirst = true;
        String prefix = "\n";
        for (int i = 0; i < tasks.size(); i++) {
            String result = "";
            Task tk = tasks.get(i);
            if (isFirst) {
                result = (i + 1) + "." + tk;
                isFirst = false;
            }  else {
                result = prefix + (i + 1) + "." + tk;
            }

            output.append(result);
        }
        return output.toString();
    }

    public static String itemsFromFile(Scanner sc) {
        String output = "";
        boolean isFirst = true;
        while (sc.hasNext()) {
            if (isFirst) {
                output += sc.nextLine();
                isFirst = false;
            } else {
                output += "\n" + sc.nextLine();
            }
        }
        return output;
    }
    public static String unableToWriteFileError() {
        return "Unable to write the file";
    }

    public void showError(String msg) {
        out.println(msg);
    }


}
