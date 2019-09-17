package duke.command;

import duke.io.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.FileNotFoundException;

public class Delete extends Command {

    /**
     * Deletes a task and returns a notification.
     * @param act command string
     * @param sto storage of current duke instance
     * @return notification string
     * @throws FileNotFoundException FileNotFoundException
     */
    public static String delete(String act, Storage sto) throws FileNotFoundException {
        int d = Integer.parseInt(act.substring(7)) - 1;
        Task temp = TaskList.taskList.get(d);
        TaskList.taskList.remove(d);
        String response = ("Noted. I've removed this task: \n" + temp.toString()
                + "\nNow you have " + (TaskList.taskList.size()) + " tasks in the list.");
        sto.saveData();
        return response;
    }
}
