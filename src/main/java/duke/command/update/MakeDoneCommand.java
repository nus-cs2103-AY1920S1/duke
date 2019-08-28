package duke.command.update;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MakeDoneCommand extends Command {

    /** Chosen Task . */
    int chosenTaskNo;

    /**
     * Constructs the MakeDoneCommand object.
     * @param chosenTaskNo Chosen Task index
     */
    public MakeDoneCommand(int chosenTaskNo) {
        this.chosenTaskNo = chosenTaskNo;
    }

    @Override
    /**
     * Marks Task as done.
     * @param tasks The current TaskList object
     * @param ui The current Ui object
     * @param storage The current Storage object
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task doneTask = tasks.doDoneTask(chosenTaskNo);
        ui.printDoneSuccess(doneTask);
    }

    @Override
    /**
     * Returns if this is an exiting command.
     * @return Whether this command exits the application
     */
    public boolean isExit() {
        return false;
    }

}
