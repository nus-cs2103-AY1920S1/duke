package duke.command;

import duke.tasklist.TaskList;

public class ListTasks extends Command {
    public static String listTasks() {
        String response = ("Here are the tasks in your list:\n");
        for (int i = 0; i < TaskList.taskList.size(); i++) {
            response += (1 + i + "." + TaskList.taskList.get(i).toString()) + "\n";
        }
        return response;
    }
}
