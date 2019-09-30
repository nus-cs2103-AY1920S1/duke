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

    public static final String GREETING = "Hello! I\'m Duke\n"
                + "What can I do for you?";

    public static final String SHOW_LINE =
            "________________________________________________";

    public static final String LEAVING_MSG = "Bye. Hope to see you again soon!";

    public static final String LOADING_ERROR = "Cannot load the file";

    public static  final String HELP_FOR_DEADLINE  = "Format: "
                + "\ndeadline <task descrip> /by <date and time>"
                + " \n\t- An deadline task of the given time will be "
                + "\n\t  added to the task list";

    public static final String HELP_FOR_EVENT = "Format: "
                + "\nevent <task descrip> /at <date and time>"
                + "\n\t- An event task with the given time will be "
                + "\n\t  added to the task list.";

    public static final String HELP_FOR_TODO = "Format: "
                + "\ntodo <task descrip> "
                + "\n\t- A todo task will be added to the task "
                + "\n\t   list.";

    public static final String HELP_FOR_DELETE = "Format: "
                + "\ndelete <number> "
                + "\n\t- The task that associated with the number "
                + "\n\t   on the list will be deleted.";

    public static final String HELP_FOR_DONE = "Format: "
                + "\ndone <number> "
                + "\n\t- The task that associated with the number on the"
                + "\n\t  list will be mark as done.";

    public static final String HELP_FOR_EXIT = "Format: "
            + "\nbye \n- You will exit the application.";


    public static final String HELP_FOR_FIND = "Format: "
                + "\nfind <keyword> "
                + "\n\t- Task that contain the keyword will be "
                + "\n\t   shown as a list.";

    public static final String HELP_FOR_LIST = "Format: "
                + "\nlist"
                + " \n\t- list of task will be displayed.";

    public static final String HELP_FOR_TIME_FORMAT = "Follow one of the following formats: "
                + "\n <dd/mmm/yyyy xx:xx>"
                + "\n <dd/mm/yyyy>";

    public static String fileSeparator = System.getProperty("file.separator");

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
        if (tasks.size() < 1) {
            return "You do not have any task in your list";
        }
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

    /**
     * display a list of content of the commands and their formate.
     * @return a whole list of all the commands
     */
    public static String helpFullContent() {
        StringBuilder content = new StringBuilder();
        content.append("Here is the list of commands \n");
        content.append("\t-deadline");
        content.append("\n\t-delete");
        content.append("\n\t-done");
        content.append("\n\t-event");
        content.append("\n\t-exit");
        content.append("\n\t-find");
        content.append("\n\t-list");
        content.append("\n\t-todo");
        content.append("\n\t-time format");
        content.append("\n Enter \"help <command>\" for detail");
        return content.toString();
    }

    /**
     * display a help for a specific command.
     * @param keyword command
     * @return helping message for that command
     */
    public static String helpForKeyword(String keyword) {
        switch (keyword) {
        case "deadline":
            return HELP_FOR_DEADLINE;
        case "todo":
            return HELP_FOR_TODO;
        case "event":
            return HELP_FOR_EVENT;
        case "delete":
            return HELP_FOR_DELETE;
        case "done":
            return HELP_FOR_DONE;
        case "exit":
            return HELP_FOR_EXIT;
        case "list":
            return HELP_FOR_LIST;
        case "find":
            return HELP_FOR_FIND;
        case "time":
            return HELP_FOR_TIME_FORMAT;
        default:
            return "Please key in a valid keyword";
        }

    }


}
