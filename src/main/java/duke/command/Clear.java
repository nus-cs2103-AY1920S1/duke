package duke.command;

import duke.io.Storage;
import duke.tasklist.TaskList;

public class Clear extends Command {

    /**
     * Clears the task list and returns a notification.
     * @return notification string
     */
    public static String clear(Storage sto) {
        String response = "Task list cleared.";
        TaskList.taskList.clear();
        sto.saveData();
        return response;
    }
}
