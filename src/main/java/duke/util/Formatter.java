package duke.util;

import duke.exception.DukeException;
import duke.task.TaskList;

public class Formatter {

    /**
     * Prints an list of tasks to a ui.
     *
     * @param tasks The list of tasks to be printed.
     */
    public static String[] formatTasks(TaskList tasks) throws DukeException {
        // Create a new String array to store the lines
        String[] lines = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            // Each line is prefixed with the item number
            lines[i] = (i + 1) + "." + tasks.get(i);
        }
        return lines;
    }
}
