package logic;

import contacts.Contact;
import task.Task;

import java.util.List;

/**
 * Deals with interactions with the user and output on CLI.
 */
public class Ui<T> {
    public static String printString = "";

    /**
     * Wraps the parameter String with horizontal lines for aesthetic purposes.
     *
     * @param input String to be wrapped
     */
    public static void loadStr(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input + "\n");
        printString = sb.toString(); //stores string in static variable that can be called later
    }

    /**
     * Method called by getResponse in Duke().
     *
     * @return String to be shown on the GUI.
     */
    public static String getLoadedStr() {
        return printString;
    }

    /**
     * Prints out greeting string at start of app.
     */
    public void greet() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append(logo);
        sb.append("Hello! I'm Duke\n");
        sb.append("What can I do for you?");
        loadStr(sb.toString());
    }

    /**
     * Prints out ordered list of tasks.
     *
     * @param list List of Tasks
     */
    public void printList(List<T> list, String command) {
        StringBuilder sb = new StringBuilder();
        switch (command) {
        case "printTask":
            sb.append("Here are the tasks in your list:\n");
            break;
        case "printFilteredTask":
            sb.append("Here are the matching tasks in your list:\n");
            break;
        case "printContacts":
            sb.append("Here is your list of contacts:\n");
            break;
        case "printFilteredContacts":
            sb.append("Here is your list of contacts matching your keyword:\n");
            break;
        default:
            break;
        }

        for (int i = 0; i < list.size(); i++) {
            sb.append((i + 1) + ". " + list.get(i).toString());
            if (i != list.size() - 1) { //last item
                sb.append("\n");
            }
        }

        loadStr(sb.toString());
    }

    /**
     * Exit Message to be printed.
     */
    public void bye() {
        loadStr("Bye. Hope to see you again soon!");
    }

}
