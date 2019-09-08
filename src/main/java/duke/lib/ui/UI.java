package duke.lib.ui;

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
     * Returns the welcome message.
     */
    public String getWelcomeMessage() {
        return format("Hello! I'm Duke",false, "What can I do for you?");
    }

    /**
     * Formats the response of duke to the user with features of indexed format and a starting message.
     *
     * @param startPhrase the starting phrase of the format.
     * @param useIndexing a flag to check if whether the format should be indexed.
     * @param lines the lines to be formatted.
     * @return the complete string format of the response.
     */
    public String format(String startPhrase, boolean useIndexing, String... lines) {
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

    /**
     * Converts a task list to string format. Meant as a helper method.
     *
     * @param startPhrase the starting phrase of the format.
     * @param tasks the task list to be converted.
     * @return the complete string format.
     */
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
