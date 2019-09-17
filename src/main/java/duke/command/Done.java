package duke.command;

import duke.io.Storage;
import duke.tasklist.TaskList;

import java.io.FileNotFoundException;

public class Done extends Command {

    /**
     * Marks a task as done and returns a notification.
     * @param act command string
     * @param sto storage of current duke instance
     * @return notification string
     * @throws FileNotFoundException FileNotFoundException
     */
    public static String done(String act, Storage sto) throws FileNotFoundException {
        int n = Integer.parseInt(act.substring(5));
        TaskList.taskList.get(n - 1).setDone();
        String response = ("Nice! I've marked this task as done:\n" + TaskList.taskList.get(n - 1).toString());
        sto.saveData();
        return response;
    }
}
