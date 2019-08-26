/**
 * This class represents a specific command of listing tasks stored in Duke.
 */
package duke.commands;
import duke.exceptions.DukeException;
import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
import java.io.IOException;

public class ListCommand extends Command {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ListCommand() {}

    /**
     * This method prints all the tasks that is stored in Duke at that point in time.
     * @param tasks contains the data structure of Tasks stored in Duke
     * @param ui contains methods dealing with interaction with the user
     * @param storage contains methods to load and save information in the file
     * @exception DukeException is thrown when there is an error with the input
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        this.ui.printLine("Here are the tasks in your list:");
        int maxNum = tasks.totalNumTasks();
        for (int i = 1; i <= maxNum; i++) {
            this.ui.printLine(i + "." + tasks.getTask(i));
        }
    }

    public boolean isExit() {
        return false;
    }
}
