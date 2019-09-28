package commands;

import logic.DukeException;
import logic.DukeList;
import logic.Storage;
import logic.TaskList;
import logic.Ui;

/**
 * Encapsulates command to delete a task from the Task List.
 */
public class DeleteTaskCommand extends TaskCommands {
    private String args;

    public DeleteTaskCommand(String args) {
        this.args = args;
    }

    /**
     * Overridden Method to execute the delete command.
     *
     * @param list    list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     * @throws DukeException If problems encountered with file update
     */
    @Override
    public void execute(DukeList list, Ui ui, Storage storage) throws DukeException {
        list.delete(args); //deletes task obj
        storage.updateTaskFile(list);
    }
}
