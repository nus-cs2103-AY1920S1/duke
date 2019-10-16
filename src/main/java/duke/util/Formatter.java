package duke.util;

import duke.task.Task;

/**
 * Initialize a formatter object which helps to format task.
 *
 */
public class Formatter {

    /**
     * Takes in a task object and format it in the format:
     * type of task|description|datetime if available.
     * @param task A task object.
     * @return The formatted string that represents the task.
     */
    public static String formatTaskForWriting(Task task) {
        StringBuilder sb = new StringBuilder();
        String className = task.getClass().getSimpleName();
        sb.append(className);
        sb.append("|");
        if (task.isDone()) {
            sb.append("1");
        } else {
            sb.append("0");
        }
        sb.append("|");
        sb.append(task.getContent());
        if (!className.equals("ToDo")) {
            sb.append("|");
            sb.append(task.getTime());
        }
        return sb.toString();
    }
}
