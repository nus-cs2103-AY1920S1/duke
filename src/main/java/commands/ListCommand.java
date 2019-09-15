package commands;

import java.util.ArrayList;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import tasks.Task;

/**
 * ListCommand is a class that displays all task items
 * that are currently in the list of tasks to the user.
 * These items can be ToDo, Event or Deadline tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     * Takes in an Array of Strings representing the full command given by the user.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public ListCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Displays all tasks that are currently on the
     * list of tasks to the user.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @return String output reply from Duke.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskLst = tasks.getTaskLst();
        StringBuilder store= new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskLst.size(); i++) {
            store.append(String.format("\n%d.%s",
                    i + 1, taskLst.get(i)));
        }
        return store.toString();
    }

}
