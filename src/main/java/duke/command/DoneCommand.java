package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a Done Command.
 */
public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Method that returns true only if this is an instance of an ExitCommand.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the Command: Sets task in taskList done, and updates the storage file.
     *
     * @param tasks   current TaskList instance
     * @param storage current Storage instance
     * @throws DukeException DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String current = tasks.get(index).storageString();
        String res = tasks.get(index).storageString();
        storage.replaceLine(current, res);
        return tasks.get(index).setDone();
    }

    /**
     * Method that checks whether this instance is logically equivalent to another Object.
     *
     * @param obj The other object in question.
     * @return true if logically equivalent, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DoneCommand) {
            DoneCommand other = (DoneCommand) obj;
            return other.index == this.index;
        }
        return false;
    }
}
