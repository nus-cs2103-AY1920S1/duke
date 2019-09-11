package commands;

import logic.DukeException;
import logic.DukeList;
import logic.Storage;
import logic.TaskList;
import logic.Ui;

/**
 * Encapsulates command to mark a task as done.
 */
public class DoneCommand extends TaskCommands {
    private String args;

    public DoneCommand(String args) {
        this.args = args;
    }

    /**
     * Overridden Method to execute the DoneCommand.
     *
     * @param list   list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     * @throws DukeException If problem encountered with file update
     */
    @Override
    public void execute(DukeList list, Ui ui, Storage storage) throws DukeException {
        ((TaskList) list).markTask(args); //will mark task.task Obj as done
        storage.updateTaskFile(list);
    }
}
