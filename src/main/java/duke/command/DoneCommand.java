package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;
import java.io.IOException;

public class DoneCommand extends Command {
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as done by its index.
     *
     * @param tasks Current list of tasks
     * @param ui UI being used
     * @param storage Current storage in use
     * @throws IOException Error with accessing file or file writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getByIndex(index);
        task.setDone(true);
        ui.showDoneMessage(task);
    }

    /**
     * Tests if {@link Command} is {@link ExitCommand}.
     *
     * @return Return true if {@link Command} is {@link ExitCommand}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
