package commands;

import logic.DukeException;
import logic.Storage;
import logic.TaskList;
import logic.Ui;

/**
 * Encapsulates command to mark a task as done.
 */
public class DoneCommand extends Command {
    private String args;

    public DoneCommand(String args) {
        this.args = args;
    }

    /**
     * Overridden Method to execute the DoneCommand.
     *
     * @param tasks   list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     * @throws DukeException If problem encountered with file update
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTask(args); //will mark task.task Obj as done
        storage.updateFile(tasks);
    }
}
