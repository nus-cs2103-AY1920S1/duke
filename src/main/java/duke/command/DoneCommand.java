package duke.command;

import duke.task.Task;
import duke.handler.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class DoneCommand extends Command {

    protected int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws IOException {
        assert index > 0 && index <= tasks.size() : ui.NO_INDEX_RESPONSE;
        Task task = tasks.get(index - 1);
        task.markAsDone();
        response = ui.doneTaskResponse(task);
        storage.save(tasks);
    }
}
