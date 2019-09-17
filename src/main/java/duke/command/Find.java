package duke.command;

import duke.io.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.LinkedList;

public class Find extends Command {
    public static String find(String act, Storage sto) {
        String keyword = act.substring(5);
        String response = "";
        boolean isFound = false;
        LinkedList<Task> foundList = new LinkedList<>();
        for (Task task : TaskList.taskList) {
            if (task.toString().contains(keyword)) {
                foundList.add(task);
                isFound = true;
            }
        }
        if (isFound) {
            response += ("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundList.size(); i++) {
                response += (1 + i + "." + foundList.get(i).toString()) + "\n";
            }
        } else {
            response += ("Keyword not found ;_;");
        }
        return response;
    }
}
