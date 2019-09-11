package commands;

import logic.DukeException;
import logic.DukeList;
import logic.Storage;
import logic.Ui;
import task.Task;

import java.util.List;

/**
 * Encapsulates command to find and filter tasks by keyword.
 */
public class FindTaskCommand extends TaskCommands {
    private String args;

    public FindTaskCommand(String args) {
        this.args = args;
    }

    /**
     * Overridden Method to exit the program.
     *
     * @param list    list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     */
    @Override
    public void execute(DukeList list, Ui ui, Storage storage) {
        List<Task> filteredTasks = list.find(args);
        ui.printList(filteredTasks, "printFilteredTask");
    }
}
