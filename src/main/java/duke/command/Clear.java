package duke.command;

import duke.tasklist.TaskList;

public class Clear extends Command {

    /**
     * Clears the task list and returns a notification.
     * @return notification string
     */
    public static String clear() {
        String response = "Task list cleared.";
        TaskList.taskList.clear();
        return response;
    }
}
