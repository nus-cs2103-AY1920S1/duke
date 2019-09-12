package command;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Abstract class for every Commands.
 * Encapsulates user's input into a command for easy handling.
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    String taskListInformation(TaskList tasks) {
        if (tasks.getSize() > 1) {
            return "Now you have " + tasks.getSize() + " tasks in the list.\n";
        } else {
            return "Now you have " + tasks.getSize() + " task in the list.\n";
        }
    }

    String iterateTaskList(ArrayList<Task> tasks) {
        String output = "";
        int i = 1;
        for (Task task : tasks) {
            output += i + ". " + task + "\n";
            i++;
        }
        return output;
    }

}
