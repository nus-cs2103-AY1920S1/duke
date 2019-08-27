package duke;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.TaskType;

public class AddCommand extends Command {
    private TaskType type;

    protected AddCommand(TaskType type, String[] args, boolean isExit) {
        super(isExit);
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {

    }
}
