package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {
    protected int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task action = tasks.getTask(index);
        if (index + 1 > tasks.getListSize()) {
            throw new DukeException("\u2639 OOPS!!! This item does not exist.");
        }
        action.setDone();
        ui.printDoneMessage(action);
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException exception) {
            ui.printException(exception);
        }
    }

    public boolean isExit() {
        return false;
    }
}
