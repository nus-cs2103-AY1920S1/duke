package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;
import java.text.ParseException;

public class UpdatePriorityCommand extends Command {
    int index;
    int priority;

    public UpdatePriorityCommand(int index, int priority) {
        this.index = index;
        this.priority = priority;
    }

    /**
     * Executes {@link Command} to update the priority of a {@link Task}.
     * @param tasks Current {@link TaskList}
     * @param ui {@link Ui} being used
     * @param storage Current {@link Storage} in use
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getByIndex(index);
        task.setPriority(priority);
        ui.showPrioritySetMessage(task, priority);
        storage.saveTaskList(tasks);
    }

    /**
     * Checks if {@link Command} is {@link ExitCommand}.
     *
     * @return true if {@link Command} is {@link ExitCommand}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
