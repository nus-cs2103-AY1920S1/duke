package duke.lib.ui;

import duke.lib.common.DukeException;
import duke.lib.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles output and input via terminal for the application.
 */
public class UI {
    private Scanner sc;
    private static final String INDENTATION = "     ";
    private static final String HORIZONTAL_LINE =
            INDENTATION + "____________________________________________________________\n";

    /**
     * Displays the welcome message.
     */
    public String getWelcomeMessage() {
        return format("Hello! I'm Duke",false, "What can I do for you?");
    }

    /**
     * Displays the error message to the user.
     *
     * @param exception exception that was thrown to be displayed
     */
//    public void showError(DukeException exception) {
//        display(exception.getMessage());
//    }

    /**
     * Reads what the user has inputted.
     *
     * @return the input in String format
//     */
//    public String readCommand() {
//        return sc.nextLine();
//    }

    /**
     * Displays the message to the user.
     *
//     * @param string the message to be displayed.
//     */
//    public void display(String string) {
//        System.out.println(HORIZONTAL_LINE + INDENTATION + string + "\n" + HORIZONTAL_LINE);
//    }

//    /**
//     * Displays the message to the user. Overloaded for start phrase to be displayed.
//     *
//     * @param string the message to be displayed.
//     * @param startPhrase the start message to be displayed before the original message.
//     */
//    public void display(String string, String startPhrase) {
//        System.out.println(HORIZONTAL_LINE + INDENTATION + startPhrase);
//        System.out.println(INDENTATION + string + "\n" + HORIZONTAL_LINE);
//    }
//
//    /**
//     * Displays the message to the user. Overloaded for start and end phrase to be displayed.
//     *
//     * @param string the message to be displayed.
//     * @param startPhrase the start message to be displayed before the original message.
//     * @param endingPhrase the end message to be displayed after the original message.
//     */
//    public void display(String string, String startPhrase, String endingPhrase) {
//        System.out.println(HORIZONTAL_LINE + INDENTATION + startPhrase);
//        System.out.println(INDENTATION + "  " + string);
//        System.out.println(INDENTATION + endingPhrase + "\n" + HORIZONTAL_LINE);


    /**
     * Displays the list to the user in indexed format with a starting message.
     *
     */
    public String format(String startPhrase, boolean useIndexing, String ... lines) {
        StringBuilder sb = new StringBuilder(startPhrase + "\n");
        if (useIndexing) {
            int i = 1;
            while (i < lines.length) {
                sb.append(INDENTATION + i + ". " + lines[i - 1] + "\n");
                i++;
            }
            sb.append(INDENTATION + i + ". " + lines[i - 1]);
        } else {
            for (String s : lines) {
                sb.append(s + "\n");
            }
        }
        return sb.toString();
    }

    public String convertTaskListToString(String startPhrase, ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder(startPhrase + "\n");
        int i = 1;
        while (i < tasks.size()) {
            sb.append(INDENTATION + i + ". " + tasks.get(i - 1) + "\n");
            i++;
        }
        sb.append(INDENTATION + i + ". " + tasks.get(i - 1));
        return sb.toString();
    }
}
