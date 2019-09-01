package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a DoneCommand which sets Task to done in TaskList.
 */

public class DoneCommand extends Command {
    /**
     * Represent position of Task in TaskList to set to done.
     */
    private int position;

    /**
     * Constructor of DoneCommand.
     * @param position Sets position to set as done as input.
     */
    public DoneCommand(int position) {
        super();
        this.position = position;
    }

    /**
     * Executes done command. Sets Task from TaskList at given position to done.
     * @param tasks Sets Task in TaskList to done.
     * @param ui Performs actions on Ui if required.
     * @param storage Saves to Storage or loads from Storage if required.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markAsDone(this.position);
    }

    /**
     * Returns true as it is not an ExitCommand.
     * @return Boolean value of whether Duke should continue running.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
