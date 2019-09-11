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
     * Default constructor with standard I/O.
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
     * Message when a command has executed successfully.
     * @param task Task object that is added to the list.
     */
    public static String addedMsg(Task task) {
        return String.format(
                "Got it. I\'ve added this task:\n  "
                        + task + "\nNow you have %s tasks in the list", Task.getNoOfTask());
    }

    /**
     * Message when a task has successfully marked done.
     * @param task Task object that marked done.
     */

    public static String markedMsg(Task task) {
        assert task.getStatusBit() == 1 : task;
        return String.format("Nice! I've marked this task as done:\n"
                        + "  " + task);
    }

    /**
     * Message when a task is deleted from the list.
     * @param task Task object that was deleted.
     */

    public static String deleteMsg(Task task) {
        return String.format(
                "Noted. I\'ve removed this task: \n  "
                        + task + "\nNow you have %d tasks in the list.", Task.getNoOfTask());
    }

    /**
     * Message when there a item on the list matched what user searched.
     * @param tasks list of item match the searched word
     * @return Message of list of items
     */
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

    /**
     * list of items stored.
     * @param tasks list of items on in the storage
     * @return Message of items in the list
     */
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

    /**
     * reads the items in the storage.
     * @param sc scanner that scan the storage file
     * @return content in the storage file
     */
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

    public static String helpFullContent() {
        StringBuilder content = new StringBuilder();
        content.append("Here is the list of commands with their format and function\n");
        content.append("\t-");
        content.append(UiText.helpForDeadline());
        content.append("\n\t-");
        content.append(UiText.helpForEvent());
        content.append("\n\t-");
        content.append(UiText.helpForTodo());
        content.append("\n\t-");
        content.append(UiText.helpForFind());
        content.append("\n\t-");
        content.append(UiText.helpForDelete());
        content.append("\n\t-");
        content.append(UiText.helpForDone());
        content.append("\n\t-");
        content.append(UiText.helpForExit());
        content.append("\n\t-");
        content.append(UiText.helpForList());
        return content.toString();
    }

    public static String helpForKeyword(String keyword) {

        switch (keyword) {
            case "deadline":
                return UiText.helpForDeadline();
            case "todo":
                return UiText.helpForTodo();
            case "event":
                return UiText.helpForEvent();
            case "delete":
                return UiText.helpForDelete();
            case "done":
                return UiText.helpForDone();
            case "exit":
                return UiText.helpForExit();
            case "list":
                return UiText.helpForList();
            case "find":
                return UiText.helpForFind();
            default:
                return "Please key in a valid keyword";
        }

    }

    public static String helpForDeadline() {
        return "deadline <task description> /by <date and time in dd/mmm/yyyy xx:xx>" +
                " an deadline task of the given time will be added to the task list";
    }

    public static String helpForEvent() {
        return "event <task description> /at <date and time in dd/mmm/yyyy xx:xx>"
                + " an event task with the given time will be added to the task list.";
    }

    public static String helpForTodo() {
        return "todo <task description> --a todo task will be added to the task list.";
    }

    public static String helpForDelete() {
        return "delete <no of the task in the list> --the task that associated with the number on the list will be deleted.";
    }

    public static String helpForDone() {
        return "done <no of the task in the list> --the task that associated with the number on the list will be mark as done.";
    }

    public static String helpForExit() {
        return "bye --you will exit the application.";
    }

    public static String helpForFind() {
        return "find <keyword> --task that contain the keyword will be shown as a list.";
    }

    public static String helpForList() {
        return "list --list of task will be displayed.";
    }
}
