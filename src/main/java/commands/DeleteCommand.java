package commands;

import logic.*;

/**
 * Encapsulates command to delete a task from the Task List
 */
public class DeleteCommand extends Command {
    private String args;

    public DeleteCommand(String args) {
        this.args = args;
    }

    /**
     * Overridden Method to execute the delete command
     *
     * @param tasks   list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     * @throws DukeException If problems encountered with file update
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.deleteTask(args); //deletes task obj
        storage.updateFile(tasks);
    }
}
