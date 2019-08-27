package duke;

import duke.storage.Storage;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx, boolean isExit) {
        super(isExit);
        this.idx = idx;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {

    }
}
