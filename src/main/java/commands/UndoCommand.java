package commands;

import exceptions.DukeException;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class UndoCommand extends Command {
    private Command commandToUndo;

    /**
     * Create an undo command.
     *
     * @param commandToUndo user's previous command
     * @throws DukeException if previous command cannot be undone
     */
    public UndoCommand(Command commandToUndo) throws DukeException {
        if (checkCommand(commandToUndo)) {
            this.commandToUndo = commandToUndo;
        } else {
            throw new DukeException("Unable to undo this command");
        }
    }

    /**
     * Ensures that the previous command is not of type
     * List, Find and Undo.
     *
     * @return true if above conditions are met
     */
    private boolean checkCommand(Command commandToUndo) {
        if (commandToUndo instanceof ListCommand
            || commandToUndo instanceof FindCommand
            || commandToUndo instanceof UndoCommand) {
            return false;
        }
        return true;
    }

    /**
     * Undoes the most recent command executed by Duke
     * by restoring the current task list to the previous
     * snapshot taken.
     * Restored list is then saved.
     *
     * @param tasks to access the list of tasks
     * @param ui to give feedback to user
     * @param storage to write changes to file
     * @return result of undo command
     * @throws DukeException if there are problems writing to file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.restoreToSnapshot();
        storage.writeToFile(tasks);
        return ui.printUndoResult(tasks);
    }
}
