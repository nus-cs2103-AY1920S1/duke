package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents an executable action Read. A <code>ReadCommand</code> object corresponds to
 * a iteration of Tasks in the TaskList and prints it in appropriate format.
 */
public class ReadCommand extends Command {

    /**
     * Performs operations of type Read on Task objects and prints them from the list of tasks.
     * Directs action of user interaction.
     *
     * @param taskList  List of Task objects.
     * @param ui Interface for user interaction.
     * @param storage Interface for read and write operations on file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printLine("Here are the tasks in your list:");
        int i = 0;
        for (Task task : taskList.getTasksList()) {
            ui.printLine(String.format("%d. %s", (i + 1), task));
            i++;
        }
    }

}