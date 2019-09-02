package duke.command;

import duke.DukeException.DukeException;
import duke.task.Task;
import duke.taskHandler.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class DoneCommand extends Command {

    protected int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        ui.showDoneResponse();
    }
}
