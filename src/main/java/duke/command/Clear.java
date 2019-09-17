package duke.command;

import duke.tasklist.TaskList;

public class Clear extends Command {
    public static String clear() {
        String response = "Task list cleared.";
        TaskList.taskList.clear();
        return response;
    }
}
